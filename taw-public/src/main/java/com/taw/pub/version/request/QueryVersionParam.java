package com.taw.pub.version.request;

import com.hawk.utility.check.CheckEnum;
import com.hawk.utility.check.CheckNull;
import com.taw.pub.version.enums.EnumModel;

public class QueryVersionParam {
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCurrenClientversion() {
		return currenClientversion;
	}

	public void setCurrenClientversion(String currenClientversion) {
		this.currenClientversion = currenClientversion;
	}

	/**
	 * 模块号
	 */
	@CheckNull
	@CheckEnum(parser=EnumModel.class)
	private String model;
	
	/**
	 * 版本号
	 */
	@CheckNull
	private String currenClientversion;
}
