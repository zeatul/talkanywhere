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
	
	/*接收者在场景唯一标识*/
	private Long receiverFpdId;
	
	/*接收者ID*/
	private Long receiverId;
	
	/*接收者昵称*/
	private String receiverNickname;
	
	/*场景ID*/
	private Long sceneId;
	
	/*场景名称*/
	private String name;
	
	/*内容*/
	private String content;
	
	/*发送者者在场景唯一标识*/
	private Long senderFpdId;
	
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
	 * @return 接收者在场景唯一标识
	 */
	public Long getReceiverFpdId(){
		return receiverFpdId;
	}
	
	/**
	 * 
	 * @param receiverFpdId 接收者在场景唯一标识
	 */	
	public void setReceiverFpdId (Long receiverFpdId) {
		this.receiverFpdId = receiverFpdId;
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
	 * @return 接收者昵称
	 */
	public String getReceiverNickname(){
		return receiverNickname;
	}
	
	/**
	 * 
	 * @param receiverNickname 接收者昵称
	 */	
	public void setReceiverNickname (String receiverNickname) {
		this.receiverNickname = receiverNickname;
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
	 * @return 场景名称
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * 
	 * @param name 场景名称
	 */	
	public void setName (String name) {
		this.name = name;
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
	 * @return 发送者者在场景唯一标识
	 */
	public Long getSenderFpdId(){
		return senderFpdId;
	}
	
	/**
	 * 
	 * @param senderFpdId 发送者者在场景唯一标识
	 */	
	public void setSenderFpdId (Long senderFpdId) {
		this.senderFpdId = senderFpdId;
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
