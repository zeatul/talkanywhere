package com.taw.pub.picture.request;

import java.util.Date;

import com.hawk.utility.check.CheckNull;

public class InsrtPictureParam {
	public Date getPhotoTime() {
		return photoTime;
	}
	public void setPhotoTime(Date photoTime) {
		this.photoTime = photoTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Long getSceneId() {
		return sceneId;
	}
	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}
	public String getSceneName() {
		return sceneName;
	}
	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}
	/**
	 * 上传文件uuid
	 */
	@CheckNull 
	private String uuid;
	/**
	 * 上传文件 用户id
	 */
	@CheckNull
	private Long userId;
	/**
	 * 上传文件用户昵称
	 */
	@CheckNull
	private String nickname;
	/**
	 * 上传文件场景id
	 */
	@CheckNull
	private Long sceneId;
	/**
	 * 上传文件场景名称
	 */
	@CheckNull
	private String sceneName;
	
	/**
	 * 拍摄地点
	 */
	private String location;
	
	/**
	 * 拍摄时间
	 */
	private Date photoTime;

}
