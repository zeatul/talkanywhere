package com.taw.scene.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.pub.mybatis.SqlParamHelper;
import com.hawk.utility.check.CheckTools;
import com.taw.pub.scene.request.QueryFootPrintParam;
import com.taw.scene.domain.FootPrintDomain;
import com.taw.scene.mapper.FootPrintMapper;

@Service
public class FootPrintService {
	
	@Autowired
	private FootPrintMapper footPrintMapper;
	
	
	public List<FootPrintDomain> search(long userId, QueryFootPrintParam queryFootPrintParam) throws Exception{
		CheckTools.check(queryFootPrintParam);
		Map<String,Object> params = SqlParamHelper.generatePageParams("last_enter_time desc", queryFootPrintParam.getOffset(),queryFootPrintParam.getLimit());
		params.put("userId", userId);
		return footPrintMapper.loadFootPrintDynamic(params);
	}

}
