package com.taw.scene.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.pub.mybatis.SqlParamHelper;
import com.hawk.pub.pkgen.PkGenerator;
import com.hawk.utility.DateTools;
import com.hawk.utility.check.CheckTools;
import com.taw.pub.scene.request.DeleteMessageParam;
import com.taw.pub.scene.request.SearchMessageParam;
import com.taw.pub.scene.request.SendMessageParam;
import com.taw.scene.domain.FootPrintDetailDomain;
import com.taw.scene.domain.MessageDomain;
import com.taw.scene.domain.SceneDomain;
import com.taw.scene.exception.FootPrintDetailNotExistsException;
import com.taw.scene.exception.InvalidFootPrintDetailException;
import com.taw.scene.exception.SceneNotExistsException;
import com.taw.scene.mapper.MessageMapper;
import com.taw.scene.mapperex.MessageExMapper;

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

	/**
	 * 发送私信
	 * @param sendMessageParam
	 * @throws Exception 
	 */
	public Long send(SendMessageParam sendMessageParam) throws Exception{
		CheckTools.check(sendMessageParam);
		
		
		Long senderId = sendMessageParam.getSenderId();
		Long sceneId = sendMessageParam.getSceneId();
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
		
		Long receiverId = footPrintDetailDomain.getUserId();
		
		SceneDomain sceneDomain = sceneService.loadSceneDomain(sceneId, true);
		
		if (sceneDomain == null)
			throw new SceneNotExistsException();
		
		/**
		 * 消息入库
		 * TODO:检测接收者是否在场
		 */
		
		MessageDomain messageDomain = new MessageDomain();
		
		messageDomain.setContent(sendMessageParam.getContent());
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
		
		messageMapper.insert(messageDomain);
		
		/**
		 * TODO:通知在线用户 或者 push 用户
		 */
		
		return messageDomain.getId();
	}
	
	/**
	 * 列出我收到的私信，按发送时间倒排序
	 * @param searchMessageParam
	 * @return
	 * @throws Exception
	 */
	public List<MessageDomain> search(SearchMessageParam searchMessageParam) throws Exception{
		CheckTools.check(searchMessageParam);
		String orderBy = "send_time desc";
		Map<String,Object> params = SqlParamHelper.generatePageParams(orderBy, searchMessageParam.getOffset(), searchMessageParam.getLimit());
		
		return messageMapper.loadDynamic(params);
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
