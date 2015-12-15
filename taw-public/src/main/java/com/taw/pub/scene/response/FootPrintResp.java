package com.taw.pub.scene.response;

import java.util.Date;

public class FootPrintResp {

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

	public Date getLastEnterTime() {
		return lastEnterTime;
	}

	public void setLastEnterTime(Date lastEnterTime) {
		this.lastEnterTime = lastEnterTime;
	}

	/*场景主键*/
	private Long sceneId;
	
	/*场景名称*/
	private String sceneName;
	
	/*最后进入时间*/
	private Date lastEnterTime;
}
