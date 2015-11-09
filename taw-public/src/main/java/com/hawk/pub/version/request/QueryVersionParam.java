package com.hawk.pub.version.request;

import com.hawk.pub.version.enums.EnumModel;
import com.hawk.utility.check.CheckEnum;
import com.hawk.utility.check.CheckNull;

public class QueryVersionParam {
	
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	@CheckNull
	@CheckEnum(parser=EnumModel.class)
	private String module;

}
