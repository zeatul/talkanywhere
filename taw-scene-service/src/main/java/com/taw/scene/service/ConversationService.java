package com.taw.scene.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.exception.BasicException;
import com.hawk.pub.enums.EnumBoolean;
import com.hawk.pub.mybatis.SqlParamHelper;
import com.hawk.pub.pkgen.PkGenerator;
import com.hawk.utility.CollectionTools;
import com.hawk.utility.DateTools;
import com.hawk.utility.JsonTools;
import com.hawk.utility.StringTools;
import com.hawk.utility.check.CheckTools;
import com.taw.picture.service.PictureService;
import com.taw.pub.picture.enums.EnumAppSrc;
import com.taw.pub.picture.request.InsrtPictureParam;
import com.taw.pub.scene.enums.EnumMessageType;
import com.taw.pub.scene.request.DeleteConversationParam;
import com.taw.pub.scene.request.ExistFootPrintParam;
import com.taw.pub.scene.request.SearchConversationParam;
import com.taw.pub.scene.request.SendConverstaionParam;
import com.taw.pub.scene.response.PicDescResp;
import com.taw.pub.scene.response.SendConverstaionResp;
import com.taw.scene.domain.ConversationDomain;
import com.taw.scene.domain.FootPrintDetailDomain;
import com.taw.scene.domain.SceneDomain;
import com.taw.scene.domain.ScenePicDomain;
import com.taw.scene.exception.FootPrintDetailNotExistsException;
import com.taw.scene.exception.InvalidFootPrintDetailException;
import com.taw.scene.exception.SceneNotExistsException;
import com.taw.scene.jms.Notification;
import com.taw.scene.jms.SceneConversationProducer;
import com.taw.scene.mapper.ConversationMapper;
import com.taw.scene.mapperex.ConversationExMapper;
import com.taw.user.domain.UserDomain;
import com.taw.user.service.UserService;



@Service
public class ConversationService {

	@Autowired
	private ConversationMapper conversationMapper;
	
	@Autowired
	private ConversationExMapper conversationExMapper;
	
	@Autowired
	private FootPrintService footPrintService;
	
	@Autowired
	private SceneService sceneService;
	
	@Autowired
	private SceneConversationProducer sceneConversationProducer;
	
	@Autowired
	private PictureService pictureService;
	
	@Autowired
	private ScenePicService scenePicService;
	
	@Autowired
	private UserService userService;
	
	private final Logger  logger = LoggerFactory.getLogger(getClass());

	/**
	 * 发送场景消息
	 * 
	 * @param sendConverstaionParam
	 * @throws Exception
	 */	
	public SendConverstaionResp send(SendConverstaionParam sendConverstaionParam) throws Exception {
		CheckTools.check(sendConverstaionParam);
		
		logger.info("SendConverstaionParam={}",JsonTools.toJsonString(sendConverstaionParam));
		
		
		String message = sendConverstaionParam.getMessage();
		List<String> pics =sendConverstaionParam.getPics();
		if (StringTools.isNullOrEmpty(message) && CollectionTools.isNullOrEmpty(pics))
			throw new BasicException("message is empty");

		Long postUserFpdId = sendConverstaionParam.getPostUserFpdId();
		Long postUserId = sendConverstaionParam.getPostUserId();
		Long sceneId = sendConverstaionParam.getSceneId();
		String sex = null;
		UserDomain userDomain = userService.loadUser(postUserId, true);
		if (userDomain != null){
			sex = userDomain.getSex();
		}
		

		FootPrintDetailDomain footPrintDetailDomain = footPrintService.loadFootPrintDetailDomain(postUserFpdId, true);

		if (footPrintDetailDomain == null)
			throw new FootPrintDetailNotExistsException();
		else if (footPrintDetailDomain.getOutTime() !=null)
			throw new InvalidFootPrintDetailException();

		if (!postUserId.equals(footPrintDetailDomain.getUserId()))
			throw new RuntimeException("UnMathed post UserId");

		if (!sceneId.equals(footPrintDetailDomain.getSceneId()))
			throw new RuntimeException("UnMathed post SceneId");
		
		
		Long rePostUserFpdId = sendConverstaionParam.getRePostUserFpdId();
		
		Long rePostId = sendConverstaionParam.getRePostId();
		Long rePostUserId = null;
		if (rePostId != null){
			
			if (rePostUserFpdId == null)
				throw new FootPrintDetailNotExistsException();
			
			footPrintDetailDomain = footPrintService.loadFootPrintDetailDomain(rePostUserFpdId, true);
			
			if (footPrintDetailDomain == null)
				throw new FootPrintDetailNotExistsException();
			
			rePostUserId = footPrintDetailDomain.getUserId();
			
			if (!footPrintDetailDomain.getSceneId().equals(sceneId))
				throw new RuntimeException("UnMathed rePost SceneId");
			
			if (!rePostUserId.equals(sendConverstaionParam.getRePostUserId()))
				throw new RuntimeException("UnMathed rePost UserId");
		}
		
		SceneDomain sceneDomain = sceneService.loadSceneDomain(sceneId, true);
		
		if (sceneDomain == null)
			throw new SceneNotExistsException();
		

		String sceneName = sceneDomain.getName();
		ConversationDomain conversationDomain = new ConversationDomain();
		conversationDomain.setMessage(message);
		conversationDomain.setPicCount(pics == null ? 0 : pics.size());
		conversationDomain.setSceneId(sendConverstaionParam.getSceneId());
		conversationDomain.setSceneName(sceneName);

		/**
		 * poster信息
		 */
		conversationDomain.setPostUserId(sendConverstaionParam.getPostUserId());
		conversationDomain.setPostNickname(sendConverstaionParam.getPostNickname());
		conversationDomain.setPostUserFpdId(sendConverstaionParam.getPostUserFpdId());

		/**
		 * rePoster信息
		 */
		
		if (rePostId != null) {
			
			/**
			 * TODO: 是否需要 校验这几个参数？
			 */
			
			conversationDomain.setRePostId(rePostId);
			conversationDomain.setRePostNickname(sendConverstaionParam.getRePostNickname());
			conversationDomain.setRePostUserId(rePostUserId);
			conversationDomain.setRePostUserFpdId(sendConverstaionParam.getRePostUserFpdId());
		}

		/**
		 * 主键，日期
		 */
		conversationDomain.setPostDate(DateTools.now());
		conversationDomain.setId(PkGenerator.genPk());

		
		
		/**
		 * 处理图片
		 */
		conversationDomain.setPicCount(0);		
		List<PicDescResp> picDescRespList = null;
		if (pics != null){
			conversationDomain.setPicCount(pics.size());
			picDescRespList = new ArrayList<PicDescResp>(pics.size());
			
			ExistFootPrintParam existFootPrintParam = new ExistFootPrintParam();
			existFootPrintParam.setSceneId(sceneId);
			existFootPrintParam.setUserId(postUserId);
			boolean onScene = sceneService.isPresentedInScene(existFootPrintParam);
			
			
			
			for (String uuid : pics){
				
				if (StringTools.isNullOrEmpty(uuid))
					continue;
				
				InsrtPictureParam insrtPictureParam = new InsrtPictureParam();
				insrtPictureParam.setUuid(uuid);
				insrtPictureParam.setUserId(postUserId);
				insrtPictureParam.setNickname(sendConverstaionParam.getPostNickname());
				insrtPictureParam.setSceneId(sceneId);
				insrtPictureParam.setSceneName(sceneName);
				insrtPictureParam.setAppSrc(EnumAppSrc.CONVERSATION);
				insrtPictureParam.setOnScene(onScene?EnumBoolean.TRUE.getValue():EnumBoolean.FALSE.getValue());
				insrtPictureParam.setDescription(sendConverstaionParam.getMessage());
				insrtPictureParam.setAppSrcId(conversationDomain.getId());
				insrtPictureParam.setSex(sex);
				/**
				 * 图片id ，插入图片管理表
				 */
				Long picId = pictureService.insrtPicture(insrtPictureParam);
				
				picDescRespList.add(new PicDescResp(picId, uuid));
				
				/**
				 * 插入场景图片关联表
				 */
				ScenePicDomain scenePicDomain = new ScenePicDomain();
				scenePicDomain.setKind(EnumMessageType.CONVERSATION.toString());
				scenePicDomain.setMid(conversationDomain.getId());
				scenePicDomain.setSceneId(sceneId);
				scenePicDomain.setSceneName(sceneName);
				scenePicDomain.setId(PkGenerator.genPk());
				
				scenePicService.inserScenePic(conversationDomain.getId(), sceneId, sceneName, EnumMessageType.CONVERSATION,picId,uuid);
			}
			
			/**
			 * 设置会话包含的图片信息
			 */
			conversationDomain.setPics(JsonTools.toJsonString(picDescRespList));
		}
		
		
		/**
		 * conversationDomain 入库
		 */
		conversationMapper.insert(conversationDomain);

		/**
		 * 通知该场景在线用户 或者 push用户
		 * TODO:做成异步
		 */
		Notification notification = new Notification();
		notification.setToken(sendConverstaionParam.getToken());
		notification.setSceneId(sceneId);
		sceneConversationProducer.send(notification);
		
		SendConverstaionResp sendConverstaionResp = new SendConverstaionResp();
		sendConverstaionResp.setId(conversationDomain.getId());
		sendConverstaionResp.setPicList(picDescRespList);
		sendConverstaionResp.setPostDate(conversationDomain.getPostDate());
		
		return sendConverstaionResp;

	}
	
	/**
	 * 查询指定场景的会话，按发送时间倒排序
	 * @param searchConverstaionParam
	 * @return
	 * @throws Exception
	 */
	public List<ConversationDomain> search(SearchConversationParam searchConverstaionParam) throws Exception{
		CheckTools.check(searchConverstaionParam);
		Date minPostDate = searchConverstaionParam.getMinPostDate();
		Date maxPostDate = searchConverstaionParam.getMaxPostDate();
		if (minPostDate == null && maxPostDate == null )
			throw new RuntimeException("MinPostDate and MaxPostDate shouldn't be null at the same time");
		
		
		String orderBy = "post_date desc";
		/**
		 * order == 1 : 按时间增序
		 * 否则 ：按时间倒序
		 */
		if (searchConverstaionParam.getOrder().intValue() == 1){
			orderBy = "post_date asc";
		}
		Map<String,Object> params = SqlParamHelper.generatePageParams(orderBy, searchConverstaionParam.getOffset(), searchConverstaionParam.getLimit());
		params.put("minPostDate", minPostDate);
		params.put("maxPostDate", maxPostDate);
		params.put("sceneId", searchConverstaionParam.getSceneId());
		params.put("postUserId", searchConverstaionParam.getPostUserId());
		
		
		List<ConversationDomain> list = conversationExMapper.searchConversationInScene(params);
		
		return list;
	}
	
	/**
	 * 删除指定场景的我发表的会话
	 * @param deleteConversationParam
	 * @throws Exception
	 */
	public void delete(DeleteConversationParam deleteConversationParam) throws Exception{
		CheckTools.check(deleteConversationParam);
		conversationExMapper.deleteByIds(deleteConversationParam.getIds(), deleteConversationParam.getUserId());
		/**
		 * TODO:删除图片？
		 */
		/**
		 * TODO: 删除回复？
		 */
	}

}
