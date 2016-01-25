package com.taw.pub.scene.request;

import com.hawk.utility.check.CheckNull;

public class QuerySceneByNameParam {
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@CheckNull
	private String name;

}
