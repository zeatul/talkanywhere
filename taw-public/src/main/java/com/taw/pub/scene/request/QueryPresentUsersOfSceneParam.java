package com.taw.pub.scene.request;

import com.hawk.utility.check.CheckNull;

public class QueryPresentUsersOfSceneParam {
	
	
	public Long getSceneId() {
		return sceneId;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	@CheckNull
	private Long sceneId;

}
