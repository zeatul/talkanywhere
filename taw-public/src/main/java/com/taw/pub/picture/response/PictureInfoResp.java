package com.taw.pub.picture.response;

import java.util.Date;

public class PictureInfoResp {

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUp() {
		return up;
	}
	public void setUp(String up) {
		this.up = up;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getSceneName() {
		return sceneName;
	}
	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}
	public Short getOnScene() {
		return onScene;
	}
	public void setOnScene(Short onScene) {
		this.onScene = onScene;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCrdt() {
		return crdt;
	}
	public void setCrdt(Date crdt) {
		this.crdt = crdt;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSurl() {
		return surl;
	}
	public void setSurl(String surl) {
		this.surl = surl;
	}
	public Long getSceneId() {
		return sceneId;
	}
	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Integer getForwardCount() {
		return forwardCount;
	}
	public void setForwardCount(Integer forwardCount) {
		this.forwardCount = forwardCount;
	}
	public Integer getUpCount() {
		return upCount;
	}
	public void setUpCount(Integer upCount) {
		this.upCount = upCount;
	}
	public Integer getDownCount() {
		return downCount;
	}
	public void setDownCount(Integer downCount) {
		this.downCount = downCount;
	}
	public Integer getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	/**
	 * 赞数量
	 */
	private Integer upCount;
	/**
	 * 踢数量
	 */
	private Integer downCount;
	/**
	 * 评论数量
	 */
	private Integer commentCount;
	
	/**
	 * 转发数量
	 */
	private Integer forwardCount;
	
	/**
	 * 图片id
	 */
	private Long id;
	
	/**
	 * 图片uuid
	 */
	private String uuid;
	
	/**
	 * 场景id
	 */
	private Long sceneId;
	
	/**
	 * 原图url
	 */
	private String url;
	
	/**
	 * 缩略图url
	 */
	private String surl;
	
	private Date crdt;
	
	/*场景名称*/
	private String sceneName;
	
	/**/
	private Short onScene;
	
	/**/
	private String description; 
	
	/*上传者昵称*/
	private String nickname;
	
	/**
	 * 0,踩， 1点赞，2未踩未点赞
	 */
	private String up = "2";
	
	/*上传者ID*/
	private Long userId;
	
	/*用户性别*/
	private String sex;

}
