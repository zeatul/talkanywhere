package com.taw.scene.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hawk.utility.check.CheckTools;
import com.taw.pub.scene.request.QueryFootPrintParam;
import com.taw.scene.domain.FootPrintDomain;

@Service
public class FootPrintService {
	
	public List<FootPrintDomain> search(long userId, QueryFootPrintParam queryFootPrintParam) throws Exception{
		CheckTools.check(queryFootPrintParam);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("_orderBy", "last_enter_time desc");
		params.put("_offset", queryFootPrintParam.getOffset());
		params.put("_limit", queryFootPrintParam.getLimit());
	}

}
