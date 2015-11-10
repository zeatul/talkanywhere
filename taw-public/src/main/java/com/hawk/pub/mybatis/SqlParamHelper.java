package com.hawk.pub.mybatis;

import java.util.HashMap;
import java.util.Map;

public class SqlParamHelper {
	
	public static Map<String,Object> generatePageParams(String orderBy , int offset ,int limit){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("_orderBy", orderBy);
		params.put("_offset", offset);
		params.put("_limit", limit);
		return params;
	}

}
