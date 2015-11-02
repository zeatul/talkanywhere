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
	
	/**/
	private Long id;
	
	/**/
	private Long receiverId;
	
	/**/
	private Long sceneId;
	
	/**/
	private String content;
	
	/**/
	private Long senderId;
	
	/**/
	private String senderNickname;
	
	/**/
	private Date sendTime;
	
	
	/**
	 * 
	 * @return 
	 */
	public Long getId(){
		return id;
	}
	
	/**
	 * 
	 * @param id 
	 */	
	public void setId (Long id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return 
	 */
	public Long getReceiverId(){
		return receiverId;
	}
	
	/**
	 * 
	 * @param receiverId 
	 */	
	public void setReceiverId (Long receiverId) {
		this.receiverId = receiverId;
	}
	
	/**
	 * 
	 * @return 
	 */
	public Long getSceneId(){
		return sceneId;
	}
	
	/**
	 * 
	 * @param sceneId 
	 */	
	public void setSceneId (Long sceneId) {
		this.sceneId = sceneId;
	}
	
	/**
	 * 
	 * @return 
	 */
	public String getContent(){
		return content;
	}
	
	/**
	 * 
	 * @param content 
	 */	
	public void setContent (String content) {
		this.content = content;
	}
	
	/**
	 * 
	 * @return 
	 */
	public Long getSenderId(){
		return senderId;
	}
	
	/**
	 * 
	 * @param senderId 
	 */	
	public void setSenderId (Long senderId) {
		this.senderId = senderId;
	}
	
	/**
	 * 
	 * @return 
	 */
	public String getSenderNickname(){
		return senderNickname;
	}
	
	/**
	 * 
	 * @param senderNickname 
	 */	
	public void setSenderNickname (String senderNickname) {
		this.senderNickname = senderNickname;
	}
	
	/**
	 * 
	 * @return 
	 */
	public Date getSendTime(){
		return sendTime;
	}
	
	/**
	 * 
	 * @param sendTime 
	 */	
	public void setSendTime (Date sendTime) {
		this.sendTime = sendTime;
	}
	


}
