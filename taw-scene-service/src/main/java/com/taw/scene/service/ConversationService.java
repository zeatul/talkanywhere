package com.taw.scene.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.pub.pkgen.PkGenerator;
import com.hawk.utility.DateTools;
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

		Long postUserFpdId = sendConverstaionParam.getPostUserFpdId();
		Long postUserId = sendConverstaionParam.getPostUserId();
		Long sceneId = sendConverstaionParam.getSceneId();

		FootPrintDetailDomain footPrintDetailDomain = footPrintService.loadFootPrintDetailDomain(postUserFpdId, true);

		if (footPrintDetailDomain == null)
			throw new FootPrintDetailNotExistsException();

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
		}
		

		ConversationDomain conversationDomain = new ConversationDomain();
		conversationDomain.setMessage(complexMessage.getText());
		conversationDomain.setPicCount(pics == null ? 0 : pics.size());
		conversationDomain.setSceneId(sendConverstaionParam.getSceneId());

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
		 * TODO:通知在线用户 或者 push 用户
		 */
		
		return conversationDomain.getId();

	}

}
