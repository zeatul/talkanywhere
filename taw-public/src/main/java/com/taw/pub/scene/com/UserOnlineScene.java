package com.taw.pub.scene.com;

public class UserOnlineScene {
	
	

	public Long getFpdId() {
		return fpdId;
	}

	public void setFpdId(Long fpdId) {
		this.fpdId = fpdId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	private Long userId;
	
	private String nickname;
	
	private String token;
	
	private Long fpdId;

}
