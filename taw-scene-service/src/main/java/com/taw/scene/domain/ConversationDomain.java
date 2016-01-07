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
	
	/*场景名称*/
	private String sceneName;
	
	/*发言者在场景唯一标识*/
	private Long postUserFpdId;
	
	/*发言者主键*/
	private Long postUserId;
	
	/*发言者昵称*/
	private String postNickname;
	
	/*发言内容*/
	private String message;
	
	/*发言图片UUID,ID集合*/
	private String pics;
	
	/*被回复的发言ID*/
	private Long rePostId;
	
	/*被回复的发言者在场景唯一标识*/
	private Long rePostUserFpdId;
	
	/*被回复的发言者ID*/
	private Long rePostUserId;
	
	/*被回复的发言者昵称*/
	private String rePostNickname;
	
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
	 * @return 场景名称
	 */
	public String getSceneName(){
		return sceneName;
	}
	
	/**
	 * 
	 * @param sceneName 场景名称
	 */	
	public void setSceneName (String sceneName) {
		this.sceneName = sceneName;
	}
	
	/**
	 * 
	 * @return 发言者在场景唯一标识
	 */
	public Long getPostUserFpdId(){
		return postUserFpdId;
	}
	
	/**
	 * 
	 * @param postUserFpdId 发言者在场景唯一标识
	 */	
	public void setPostUserFpdId (Long postUserFpdId) {
		this.postUserFpdId = postUserFpdId;
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
	 * @return 发言图片UUID,ID集合
	 */
	public String getPics(){
		return pics;
	}
	
	/**
	 * 
	 * @param pics 发言图片UUID,ID集合
	 */	
	public void setPics (String pics) {
		this.pics = pics;
	}
	
	/**
	 * 
	 * @return 被回复的发言ID
	 */
	public Long getRePostId(){
		return rePostId;
	}
	
	/**
	 * 
	 * @param rePostId 被回复的发言ID
	 */	
	public void setRePostId (Long rePostId) {
		this.rePostId = rePostId;
	}
	
	/**
	 * 
	 * @return 被回复的发言者在场景唯一标识
	 */
	public Long getRePostUserFpdId(){
		return rePostUserFpdId;
	}
	
	/**
	 * 
	 * @param rePostUserFpdId 被回复的发言者在场景唯一标识
	 */	
	public void setRePostUserFpdId (Long rePostUserFpdId) {
		this.rePostUserFpdId = rePostUserFpdId;
	}
	
	/**
	 * 
	 * @return 被回复的发言者ID
	 */
	public Long getRePostUserId(){
		return rePostUserId;
	}
	
	/**
	 * 
	 * @param rePostUserId 被回复的发言者ID
	 */	
	public void setRePostUserId (Long rePostUserId) {
		this.rePostUserId = rePostUserId;
	}
	
	/**
	 * 
	 * @return 被回复的发言者昵称
	 */
	public String getRePostNickname(){
		return rePostNickname;
	}
	
	/**
	 * 
	 * @param rePostNickname 被回复的发言者昵称
	 */	
	public void setRePostNickname (String rePostNickname) {
		this.rePostNickname = rePostNickname;
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
