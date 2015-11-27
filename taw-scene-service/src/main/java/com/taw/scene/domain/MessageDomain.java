package com.taw.scene.domain;
import java.io.Serializable;
import java.util.Date;



/**
 * t_tm_message
 * 
 * 
 * @author Code-Gen
 */
public class MessageDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/*主键*/
	private Long id;
	
	/*接收者ID*/
	private Long receiverId;
	
	/*场景ID*/
	private Long sceneId;
	
	/*内容*/
	private String content;
	
	/*发送者ID*/
	private Long senderId;
	
	/*发送者昵称*/
	private String senderNickname;
	
	/*发送时间*/
	private Date sendTime;
	
	
	/**
	 * 
	 * @return 主键
	 */
	public Long getId(){
		return id;
	}
	
	/**
	 * 
	 * @param id 主键
	 */	
	public void setId (Long id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return 接收者ID
	 */
	public Long getReceiverId(){
		return receiverId;
	}
	
	/**
	 * 
	 * @param receiverId 接收者ID
	 */	
	public void setReceiverId (Long receiverId) {
		this.receiverId = receiverId;
	}
	
	/**
	 * 
	 * @return 场景ID
	 */
	public Long getSceneId(){
		return sceneId;
	}
	
	/**
	 * 
	 * @param sceneId 场景ID
	 */	
	public void setSceneId (Long sceneId) {
		this.sceneId = sceneId;
	}
	
	/**
	 * 
	 * @return 内容
	 */
	public String getContent(){
		return content;
	}
	
	/**
	 * 
	 * @param content 内容
	 */	
	public void setContent (String content) {
		this.content = content;
	}
	
	/**
	 * 
	 * @return 发送者ID
	 */
	public Long getSenderId(){
		return senderId;
	}
	
	/**
	 * 
	 * @param senderId 发送者ID
	 */	
	public void setSenderId (Long senderId) {
		this.senderId = senderId;
	}
	
	/**
	 * 
	 * @return 发送者昵称
	 */
	public String getSenderNickname(){
		return senderNickname;
	}
	
	/**
	 * 
	 * @param senderNickname 发送者昵称
	 */	
	public void setSenderNickname (String senderNickname) {
		this.senderNickname = senderNickname;
	}
	
	/**
	 * 
	 * @return 发送时间
	 */
	public Date getSendTime(){
		return sendTime;
	}
	
	/**
	 * 
	 * @param sendTime 发送时间
	 */	
	public void setSendTime (Date sendTime) {
		this.sendTime = sendTime;
	}
	


}
