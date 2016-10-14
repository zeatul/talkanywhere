package com.taw.pub.version.request;

import com.hawk.pub.enums.EnumBoolean;
import com.hawk.utility.check.CheckEnum;
import com.hawk.utility.check.CheckNull;
import com.taw.pub.version.enums.EnumModel;

public class AddVersionParam {
	
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Short getForced() {
		return forced;
	}

	public void setForced(Short forced) {
		this.forced = forced;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	 * 强迫更新
	 */
	@CheckNull
	@CheckEnum(parser=EnumBoolean.class)
	private Short forced;
	
	/**
	 * 更新描述
	 */
	private String description;
	
	/**
	 * 版本号
	 */
	@CheckNull
	private String version;

}
