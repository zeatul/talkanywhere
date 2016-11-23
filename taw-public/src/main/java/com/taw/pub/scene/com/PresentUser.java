package com.taw.pub.scene.com;

public class PresentUser {
	


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

	public Long getFpdId() {
		return fpdId;
	}

	public void setFpdId(Long fpdId) {
		this.fpdId = fpdId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	private Long userId;
	
	private String nickname;
	
	
	private Long fpdId;
	
	/*用户性别*/
	private String sex;

}
