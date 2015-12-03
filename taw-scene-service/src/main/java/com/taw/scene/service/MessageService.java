package com.taw.scene.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.pub.pkgen.PkGenerator;
import com.hawk.utility.DateTools;
import com.hawk.utility.check.CheckTools;
import com.taw.pub.scene.request.SendMessageParam;
import com.taw.scene.domain.FootPrintDetailDomain;
import com.taw.scene.domain.MessageDomain;
import com.taw.scene.exception.FootPrintDetailNotExistsException;
import com.taw.scene.mapper.MessageMapper;

@Service
public class MessageService {
	
	@Autowired
	private MessageMapper messageMapper;
	
	@Autowired
	private FootPrintService footPrintService;

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
		
		if (!senderId.equals(footPrintDetailDomain.getUserId()))
			throw new RuntimeException("UnMathed Sender UserId");
		
		if (!sceneId.equals(footPrintDetailDomain.getSceneId()))
			throw new RuntimeException("UnMathed Sender SceneId");
		
		Long receiverFpdId = sendMessageParam.getReceiverFpdId();
		
		
		footPrintDetailDomain = footPrintService.loadFootPrintDetailDomain(receiverFpdId,true);
		if (footPrintDetailDomain == null)
			throw new FootPrintDetailNotExistsException();		
		
		if (!sceneId.equals(footPrintDetailDomain.getSceneId()))
			throw new RuntimeException("UnMathed Receiver SceneId");
		
		Long receiverId = footPrintDetailDomain.getUserId();
		
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
}
