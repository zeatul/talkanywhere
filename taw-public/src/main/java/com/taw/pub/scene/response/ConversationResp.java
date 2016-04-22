package com.taw.pub.scene.response;

import java.util.Date;
import java.util.List;

public class ConversationResp {
	

	


	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Short getOnScene() {
		return onScene;
	}

	public void setOnScene(Short onScene) {
		this.onScene = onScene;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<PicDescResp> getPicList() {
		return picList;
	}

	public void setPicList(List<PicDescResp> picList) {
		this.picList = picList;
	}

	/*主键*/
	private Long id;
	
	/*场景主键*/
	private Long sceneId;
	
	/*发言者在场景唯一标识*/
	private Long postUserFpdId;
	
	/*发言者主键*/
	private Long postUserId;
	
	/*发言者昵称*/
	private String postNickname;
	
	/*发言内容*/
	private String message;
	
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
	 * 发言者性别
	 */
	private String sex;
	
	/**
	 * 图片集合
	 */
	private List<PicDescResp> picList;
	
	
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
	
	
	/**
	 * 发言用户是否正在发言场景
	 */
	private Short onScene;
	
}
