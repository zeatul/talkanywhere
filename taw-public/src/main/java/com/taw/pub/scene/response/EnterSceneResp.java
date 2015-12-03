package com.taw.pub.scene.response;

public class EnterSceneResp {
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Long getFpdId() {
		return fpdId;
	}

	public void setFpdId(Long fpdId) {
		this.fpdId = fpdId;
	}

	/**
	 * 用户在指定场景的唯一标识
	 */
	private Long fpdId;
	
	/**
	 * 用户在指定场景的昵称
	 */
	private String nickName;

}
