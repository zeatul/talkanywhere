package com.taw.scene.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.pub.mybatis.SqlParamHelper;
import com.hawk.pub.pkgen.PkGenerator;
import com.hawk.utility.DateTools;
import com.hawk.utility.check.CheckTools;
import com.taw.pub.scene.request.ComplexMessage;
import com.taw.pub.scene.request.DeleteConversationParam;
import com.taw.pub.scene.request.SearchConversationParam;
import com.taw.pub.scene.request.SendConverstaionParam;
import com.taw.scene.domain.ConversationDomain;
import com.taw.scene.domain.FootPrintDetailDomain;
import com.taw.scene.domain.SceneDomain;
import com.taw.scene.exception.FootPrintDetailNotExistsException;
import com.taw.scene.exception.InvalidFootPrintDetailException;
import com.taw.scene.exception.SceneNotExistsException;
import com.taw.scene.jms.Notification;
import com.taw.scene.jms.SceneConversationProducer;
import com.taw.scene.mapper.ConversationMapper;
import com.taw.scene.mapperex.ConversationExMapper;

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

	/**
	 * 发送场景消息
	 * 
	 * @param sendConverstaionParam
	 * @throws Exception
	 */
	public Long send(SendConverstaionParam sendConverstaionParam) throws Exception {
		CheckTools.check(sendConverstaionParam);
		ComplexMessage complexMessage = sendConverstaionParam.getComplexMessage();
		List<String> pics = complexMessage.getPics();
		CheckTools.check(complexMessage);

		Long postUserFpdId = sendConverstaionParam.getPostUserFpdId();
		Long postUserId = sendConverstaionParam.getPostUserId();
		Long sceneId = sendConverstaionParam.getSceneId();

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
		

		ConversationDomain conversationDomain = new ConversationDomain();
		conversationDomain.setMessage(complexMessage.getText());
		conversationDomain.setPicCount(pics == null ? 0 : pics.size());
		conversationDomain.setSceneId(sendConverstaionParam.getSceneId());
		conversationDomain.setSceneName(sceneDomain.getName());

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
		 * 入库
		 */
		conversationMapper.insert(conversationDomain);

		/**
		 * 通知该场景在线用户 或者 push用户
		 */
		Notification notification = new Notification();
		notification.setToken(sendConverstaionParam.getToken());
		notification.setSceneId(sceneId);
		sceneConversationProducer.send(notification);
		
		return conversationDomain.getId();

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
