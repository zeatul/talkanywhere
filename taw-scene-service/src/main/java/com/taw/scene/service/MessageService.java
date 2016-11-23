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
import com.taw.pub.scene.request.DeleteMessageParam;
import com.taw.pub.scene.request.ExistFootPrintParam;
import com.taw.pub.scene.request.SearchMessageParam;
import com.taw.pub.scene.request.SendMessageParam;
import com.taw.pub.scene.response.PicDescResp;
import com.taw.pub.scene.response.SendMessageResp;
import com.taw.scene.domain.FootPrintDetailDomain;
import com.taw.scene.domain.MessageDomain;
import com.taw.scene.domain.SceneDomain;
import com.taw.scene.domain.ScenePicDomain;
import com.taw.scene.exception.FootPrintDetailNotExistsException;
import com.taw.scene.exception.InvalidFootPrintDetailException;
import com.taw.scene.exception.SceneNotExistsException;
import com.taw.scene.exception.UserNotEnterSceneException;
import com.taw.scene.jms.Notification;
import com.taw.scene.jms.SceneMessageProducer;
import com.taw.scene.mapper.MessageMapper;
import com.taw.scene.mapperex.FootPrintDetailExMapper;
import com.taw.scene.mapperex.MessageExMapper;
import com.taw.user.domain.UserDomain;
import com.taw.user.service.UserService;

@Service
public class MessageService {
	
	@Autowired
	private MessageMapper messageMapper;
	
	@Autowired
	private MessageExMapper messageExMapper;
	
	@Autowired
	private FootPrintService footPrintService;
	
	@Autowired
	private SceneService sceneService;
	
	@Autowired
	private SceneMessageProducer sceneMessageProducer;
	
	@Autowired
	private ScenePicService scenePicService;
	
	@Autowired
	private PictureService pictureService;
	
	@Autowired
	private UserService userService;
	
	
	
	private final Logger  logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 发送私信
	 * @param sendMessageParam
	 * @throws Exception 
	 */
	
	public SendMessageResp send(SendMessageParam sendMessageParam) throws Exception{
		CheckTools.check(sendMessageParam);
		
		logger.info("SendMessageParam={}",JsonTools.toJsonString(sendMessageParam));
		
		String message = sendMessageParam.getMessage();
		List<String> pics =sendMessageParam.getPics();
		if (StringTools.isNullOrEmpty(message) && CollectionTools.isNullOrEmpty(pics))
			throw new BasicException("message is empty");
		
		
		Long senderId = sendMessageParam.getSenderId();
		Long sceneId = sendMessageParam.getSceneId();
		String sex = null;
		UserDomain userDomain = userService.loadUser(senderId, true);
		if (userDomain != null){
			sex = userDomain.getSex();
		}
		
		
		/**
		 * 进入场景 或者 物理在场
		 */
		Long senderFpdId = sendMessageParam.getSenderFpdId();
		
		FootPrintDetailDomain footPrintDetailDomain = footPrintService.loadFootPrintDetailDomain(senderFpdId,true);
		
		if (footPrintDetailDomain == null)
			throw new FootPrintDetailNotExistsException();
		else if (footPrintDetailDomain.getOutTime() !=null)
			throw new InvalidFootPrintDetailException();
			
		
		if (!senderId.equals(footPrintDetailDomain.getUserId()))
			throw new RuntimeException("UnMathed Sender UserId");
		
		if (!sceneId.equals(footPrintDetailDomain.getSceneId()))
			throw new RuntimeException("UnMathed Sender SceneId");
		
		Long receiverFpdId = sendMessageParam.getReceiverFpdId();
		
		Long receiverId = sendMessageParam.getReceiverId();
		
		SceneDomain sceneDomain = sceneService.loadSceneDomain(sceneId, true);
		
		if (sceneDomain == null)
			throw new SceneNotExistsException();
		
		/**
		 * 检测接收者是否进入场景
		 */
		ExistFootPrintParam existFootPrintParam = new ExistFootPrintParam();
		existFootPrintParam.setSceneId(sceneId);
		existFootPrintParam.setUserId(receiverId);
		if (!footPrintService.hasEnteredScene(existFootPrintParam)){
			throw new UserNotEnterSceneException();
		}
		
		
			
		
		MessageDomain messageDomain = new MessageDomain();
		
		messageDomain.setMessage(message);
		messageDomain.setReceiverFpdId(receiverFpdId);
		messageDomain.setReceiverId(receiverId);
		messageDomain.setReceiverNickname(sendMessageParam.getReceiverNickname());
		messageDomain.setSceneId(sceneId);
		messageDomain.setSceneName(sceneDomain.getName());
		messageDomain.setSenderFpdId(senderFpdId);
		messageDomain.setSenderId(senderId);
		messageDomain.setSenderNickname(footPrintDetailDomain.getNickname());	
		
		
		messageDomain.setId(PkGenerator.genPk());
		messageDomain.setSendTime(DateTools.now());
		
		/**
		 * 处理图片
		 */
		messageDomain.setPicCount(0);
		List<PicDescResp> picDescRespList = null;
		if (pics != null){
			messageDomain.setPicCount(pics.size());
			picDescRespList = new ArrayList<PicDescResp>(pics.size());
			for (String uuid : pics){
				InsrtPictureParam insrtPictureParam = new InsrtPictureParam();
				insrtPictureParam.setUuid(uuid);
				insrtPictureParam.setUserId(senderId);
				insrtPictureParam.setNickname(footPrintDetailDomain.getNickname());
				insrtPictureParam.setSceneId(sceneId);
				insrtPictureParam.setSceneName(sceneDomain.getName());
				insrtPictureParam.setAppSrc(EnumAppSrc.MESSAGE);
				insrtPictureParam.setAppSrcId(messageDomain.getId());
				insrtPictureParam.setDescription(sendMessageParam.getMessage());
				insrtPictureParam.setOnScene(EnumBoolean.TRUE.getValue());
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
				scenePicDomain.setMid(messageDomain.getId());
				scenePicDomain.setSceneId(sceneId);
				scenePicDomain.setSceneName(sceneDomain.getName());
				scenePicDomain.setId(PkGenerator.genPk());
				
				scenePicService.inserScenePic(messageDomain.getId(), sceneId, sceneDomain.getName(), EnumMessageType.MESSAGE,picId,uuid);
			}
			
			/**
			 * 设置会话包含的图片信息
			 */
			messageDomain.setPics(JsonTools.toJsonString(picDescRespList));
		}
		
		
		messageMapper.insert(messageDomain);
		
		/**
		 * 通知在线接收用户 或者 push 用户
		 * TODO:做成异步
		 */
		Notification notification = new Notification();
		notification.setUserId(receiverId);
		sceneMessageProducer.send(notification);
		
		SendMessageResp sendMessageResp = new SendMessageResp();
		sendMessageResp.setId(messageDomain.getId());
		sendMessageResp.setPicList(picDescRespList);
		sendMessageResp.setSendTime(messageDomain.getSendTime());
		return sendMessageResp;
	}
	
	/**
	 * 列出我收到的私信，按发送时间倒排序
	 * @param searchMessageParam
	 * @return
	 * @throws Exception
	 */
	public List<MessageDomain> search(SearchMessageParam searchMessageParam) throws Exception{
		CheckTools.check(searchMessageParam);
		Date minPostDate = searchMessageParam.getMinPostDate();
		Date maxPostDate = searchMessageParam.getMaxPostDate();
		if (minPostDate == null && maxPostDate == null )
			throw new RuntimeException("MinPostDate and MaxPostDate shouldn't be null at the same time");
		String orderBy = "send_time desc";
		
		/**
		 * order == 1 : 按时间增序
		 * 否则 ：按时间倒序
		 */
		if (searchMessageParam.getOrder().intValue() == 1){
			orderBy = "send_time asc";
		}
		
		Map<String,Object> params = SqlParamHelper.generatePageParams(orderBy, searchMessageParam.getOffset(), searchMessageParam.getLimit());
		
		params.put("minPostDate", minPostDate);
		params.put("maxPostDate", maxPostDate);
		params.put("sceneId", searchMessageParam.getSceneId());
		params.put("myId", searchMessageParam.getUserId()); //自身Id
		/**
		 * 是否包含自身发言
		 */
		if (searchMessageParam.getIncludeSelf() == 1){
			params.put("includeSelf", "1");
		}
		
		Long partyId = searchMessageParam.getPartyId(); //对话者Id ，为空，取所有发言者
		params.put("partyId", partyId);

		
		return messageExMapper.searchMessage(params);
	}
	
	/**
	 * 删除我收到的指定私信
	 * @param deleteMessageParam
	 * @throws Exception
	 */
	public void delete(DeleteMessageParam deleteMessageParam)throws Exception{
		CheckTools.check(deleteMessageParam);
		messageExMapper.deleteByIds(deleteMessageParam.getIds(), deleteMessageParam.getUserId());
	}
}
