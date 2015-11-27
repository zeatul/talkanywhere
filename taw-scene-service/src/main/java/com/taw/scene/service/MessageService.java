package com.taw.scene.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.pub.pkgen.PkGenerator;
import com.hawk.utility.DateTools;
import com.hawk.utility.check.CheckTools;
import com.taw.pub.scene.request.SendMessageParam;
import com.taw.scene.domain.FootPrintDetailDomain;
import com.taw.scene.domain.MessageDomain;
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
	public void send(SendMessageParam sendMessageParam) throws Exception{
		CheckTools.check(sendMessageParam);
		
		MessageDomain messageDomain = new MessageDomain();
		
		messageDomain.setContent(sendMessageParam.getContent());
		messageDomain.setReceiverId(sendMessageParam.getReceiverId());
		messageDomain.setSceneId(sendMessageParam.getSceneId());
		messageDomain.setSenderId(sendMessageParam.getSceneId());
		
		FootPrintDetailDomain footPrintDetailDomain = footPrintService.loadFootPrintDetailDomain(sendMessageParam.getFpdId());
		
		messageDomain.setSenderNickname(footPrintDetailDomain.getNickname());
		
		footPrintService.loadFootPrintDetailDomain(sendMessageParam.getFpdId());
		
		messageDomain.setId(PkGenerator.genPk());
		messageDomain.setSendTime(DateTools.now());
		
		messageMapper.insert(messageDomain);
		
		/**
		 * TODO:通知在线用户
		 */
	}
}
