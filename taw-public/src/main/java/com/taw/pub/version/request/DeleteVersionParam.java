package com.taw.pub.version.request;

import com.hawk.utility.check.CheckEnum;
import com.hawk.utility.check.CheckNull;
import com.taw.pub.version.enums.EnumModel;

public class DeleteVersionParam {
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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
	private String version;
}
