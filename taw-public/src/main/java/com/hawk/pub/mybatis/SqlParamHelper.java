package com.hawk.pub.mybatis;

import java.util.HashMap;
import java.util.Map;

public class SqlParamHelper {
	
	public static Map<String,Object> generatePageParams(String orderby , int offset ,int limit){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("_orderby", orderby);
		params.put("_offset", offset);
		params.put("_limit", limit);
		return params;
	}

}
