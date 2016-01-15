package com.taw.scene.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hawk.exception.BasicException;
import com.hawk.pub.mybatis.SqlParamHelper;
import com.hawk.pub.pkgen.PkGenerator;
import com.hawk.utility.CollectionTools;
import com.hawk.utility.DateTools;
import com.hawk.utility.JsonTools;
import com.hawk.utility.StringTools;
import com.hawk.utility.check.CheckTools;
import com.taw.picture.service.PictureService;
import com.taw.pub.picture.request.InsrtPictureParam;
import com.taw.pub.scene.enums.EnumMessageType;
import com.taw.pub.scene.request.DeleteMessageParam;
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
import com.taw.scene.jms.Notification;
import com.taw.scene.jms.SceneMessageProducer;
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
	
	@Autowired
	private SceneMessageProducer sceneMessageProducer;
	
	@Autowired
	private ScenePicService scenePicService;
	
	@Autowired
	private PictureService pictureService;

	/**
	 * 发送私信
	 * @param sendMessageParam
	 * @throws Exception 
	 */
	
	public SendMessageResp send(SendMessageParam sendMessageParam) throws Exception{
		CheckTools.check(sendMessageParam);
		
		String message = sendMessageParam.getMessage();
		List<String> pics =sendMessageParam.getPics();
		if (StringTools.isNullOrEmpty(message) && CollectionTools.isNullOrEmpty(pics))
			throw new BasicException("message is empty");
		
		
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
