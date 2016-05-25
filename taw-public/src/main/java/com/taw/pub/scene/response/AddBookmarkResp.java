package com.taw.pub.scene.response;

public class AddBookmarkResp {
	
	public Long getSceneId() {
		return sceneId;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	private Long sceneId;
	
	/**
	 * 1:收藏成功
	 * 2：已收藏
	 * 3:场景不存在
	 */
	private Integer result ;

}
