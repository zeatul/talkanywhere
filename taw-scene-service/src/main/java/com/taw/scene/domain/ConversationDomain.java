package com.taw.scene.domain;
import java.io.Serializable;
import java.util.Date;



/**
 * t_tm_conversation
 * 
 * 
 * @author Code-Gen
 */
public class ConversationDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/*主键*/
	private Long id;
	
	/*场景主键*/
	private Long sceneId;
	
	/*发言者主键*/
	private Long postUserId;
	
	/*发言者昵称*/
	private String postNickname;
	
	/*发言内容*/
	private String message;
	
	/*被回复的发言ID*/
	private Long rPostId;
	
	/*被回复的发言者ID*/
	private Long rPostUserId;
	
	/*被回复的发言者昵称*/
	private String rPostNickname;
	
	/*包含的图片数量*/
	private Integer picCount;
	
	/*发言时间*/
	private Date postDate;
	
	
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
	 * @return 场景主键
	 */
	public Long getSceneId(){
		return sceneId;
	}
	
	/**
	 * 
	 * @param sceneId 场景主键
	 */	
	public void setSceneId (Long sceneId) {
		this.sceneId = sceneId;
	}
	
	/**
	 * 
	 * @return 发言者主键
	 */
	public Long getPostUserId(){
		return postUserId;
	}
	
	/**
	 * 
	 * @param postUserId 发言者主键
	 */	
	public void setPostUserId (Long postUserId) {
		this.postUserId = postUserId;
	}
	
	/**
	 * 
	 * @return 发言者昵称
	 */
	public String getPostNickname(){
		return postNickname;
	}
	
	/**
	 * 
	 * @param postNickname 发言者昵称
	 */	
	public void setPostNickname (String postNickname) {
		this.postNickname = postNickname;
	}
	
	/**
	 * 
	 * @return 发言内容
	 */
	public String getMessage(){
		return message;
	}
	
	/**
	 * 
	 * @param message 发言内容
	 */	
	public void setMessage (String message) {
		this.message = message;
	}
	
	/**
	 * 
	 * @return 被回复的发言ID
	 */
	public Long getRPostId(){
		return rPostId;
	}
	
	/**
	 * 
	 * @param rPostId 被回复的发言ID
	 */	
	public void setRPostId (Long rPostId) {
		this.rPostId = rPostId;
	}
	
	/**
	 * 
	 * @return 被回复的发言者ID
	 */
	public Long getRPostUserId(){
		return rPostUserId;
	}
	
	/**
	 * 
	 * @param rPostUserId 被回复的发言者ID
	 */	
	public void setRPostUserId (Long rPostUserId) {
		this.rPostUserId = rPostUserId;
	}
	
	/**
	 * 
	 * @return 被回复的发言者昵称
	 */
	public String getRPostNickname(){
		return rPostNickname;
	}
	
	/**
	 * 
	 * @param rPostNickname 被回复的发言者昵称
	 */	
	public void setRPostNickname (String rPostNickname) {
		this.rPostNickname = rPostNickname;
	}
	
	/**
	 * 
	 * @return 包含的图片数量
	 */
	public Integer getPicCount(){
		return picCount;
	}
	
	/**
	 * 
	 * @param picCount 包含的图片数量
	 */	
	public void setPicCount (Integer picCount) {
		this.picCount = picCount;
	}
	
	/**
	 * 
	 * @return 发言时间
	 */
	public Date getPostDate(){
		return postDate;
	}
	
	/**
	 * 
	 * @param postDate 发言时间
	 */	
	public void setPostDate (Date postDate) {
		this.postDate = postDate;
	}
	


}
