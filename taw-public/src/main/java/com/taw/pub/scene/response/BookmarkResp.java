package com.taw.pub.scene.response;

import java.util.Date;

public class BookmarkResp {

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getBookTime() {
		return bookTime;
	}

	public void setBookTime(Date bookTime) {
		this.bookTime = bookTime;
	}

	/*主键*/
	private Long id;
	
	/*场景主键*/
	private Long sceneId;
	
	/*场景名称*/
	private String sceneName;
	
	/*收藏时间*/
	private Date bookTime;
}
