package com.taw.scene.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.pub.pkgen.PkGenerator;
import com.hawk.utility.DateTools;
import com.hawk.utility.DomainTools;
import com.hawk.utility.check.CheckTools;
import com.taw.pub.scene.request.ComplexMessage;
import com.taw.pub.scene.request.SendConverstaionParam;
import com.taw.scene.domain.ConversationDomain;
import com.taw.scene.domain.FootPrintDetailDomain;
import com.taw.scene.exception.FootPrintDetailNotExistsException;
import com.taw.scene.mapper.ConversationMapper;

@Service
public class ConversationService {

	@Autowired
	private ConversationMapper conversationMapper;
	
	@Autowired
	private FootPrintService footPrintService;

	/**
	 * 发送场景消息
	 * 
	 * @param sendConverstaionParam
	 * @throws Exception
	 */
	public Long send(SendConverstaionParam sendConverstaionParam) throws Exception {
		CheckTools.check(sendConverstaionParam);
		ComplexMessage complexMessage = sendConverstaionParam.getMessage();
		List<String> pics = complexMessage.getPics();
		CheckTools.check(complexMessage);

		Long fpdId = sendConverstaionParam.getFpdId();
		Long postUserId = sendConverstaionParam.getPostUserId();
		Long sceneId = sendConverstaionParam.getSceneId();

		FootPrintDetailDomain footPrintDetailDomain = footPrintService.loadFootPrintDetailDomain(fpdId, true);

		if (footPrintDetailDomain == null)
			throw new FootPrintDetailNotExistsException();

		if (postUserId != footPrintDetailDomain.getUserId())
			throw new RuntimeException("UnMathed UserId");

		if (sceneId != footPrintDetailDomain.getSceneId())
			throw new RuntimeException("UnMathed SceneId");

		ConversationDomain conversationDomain = new ConversationDomain();
		conversationDomain.setMessage(complexMessage.getTextMessage());
		conversationDomain.setPicCount(pics == null ? 0 : pics.size());
		conversationDomain.setSceneId(sendConverstaionParam.getSceneId());

		/**
		 * poster信息
		 */
		conversationDomain.setPostUserId(sendConverstaionParam.getPostUserId());
		conversationDomain.setPostNickname(sendConverstaionParam.getrPostNickname());

		/**
		 * rposter信息
		 */
		Long rPostId = sendConverstaionParam.getrPostId();
		if (rPostId != null) {
			conversationDomain.setRPostId(rPostId);
			conversationDomain.setRPostNickname(sendConverstaionParam.getrPostNickname());
			conversationDomain.setRPostUserId(sendConverstaionParam.getrPostUserId());
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
		 * TODO:通知在线用户 或者 push 用户
		 */
		
		return conversationDomain.getId();

	}

}
