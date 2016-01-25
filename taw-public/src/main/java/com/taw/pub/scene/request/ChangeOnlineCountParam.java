package com.taw.pub.scene.request;

import java.util.List;

public class ChangeOnlineCountParam {
	
	public List<Long> getInList() {
		return inList;
	}

	public void setInList(List<Long> inList) {
		this.inList = inList;
	}

	public List<Long> getOutList() {
		return outList;
	}

	public void setOutList(List<Long> outList) {
		this.outList = outList;
	}

	/**
	 * 进入场景
	 */
	private List<Long> inList;
	
	/**
	 * 离开场景
	 */
	private List<Long> outList;

}
