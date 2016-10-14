package com.taw.user.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.pub.exception.ObjectNotFoundException;
import com.hawk.pub.pkgen.PkGenerator;
import com.hawk.utility.DateTools;
import com.hawk.utility.DomainTools;
import com.hawk.utility.check.CheckTools;
import com.taw.pub.version.request.AddVersionParam;
import com.taw.pub.version.request.DeleteVersionParam;
import com.taw.pub.version.request.QueryVersionParam;
import com.taw.pub.version.response.QueryVersionResponse;
import com.taw.user.domain.VersionDomain;
import com.taw.user.mapper.VersionMapper;

@Service
public class VersionService {

	@Autowired
	private VersionMapper versionMapper;

	public void addVersion(AddVersionParam addVersionParam) throws Exception {
		CheckTools.check(addVersionParam);
		VersionDomain versionDomain = new VersionDomain();
		DomainTools.copy(addVersionParam, versionDomain);
		versionDomain.setCrdt(DateTools.now());
		versionDomain.setUpdt(versionDomain.getCrdt());
		versionDomain.setId(PkGenerator.genPk());
		versionMapper.insert(versionDomain);
	}

	public void deleteVersion(DeleteVersionParam deleteVersionParam) throws Exception {
		CheckTools.check(deleteVersionParam);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("version", deleteVersionParam.getVersion());
		params.put("model", deleteVersionParam.getModel());
		List<VersionDomain> list = versionMapper.loadDynamic(params);
		if (list == null || list.size() == 0)
			return;
		if (list.size() > 1)
			throw new Exception("size > 0");
		versionMapper.delete(list.get(0).getId());
	}

	public QueryVersionResponse queryVersion(QueryVersionParam queryVersionParam) throws Exception {
		CheckTools.check(queryVersionParam);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("model", queryVersionParam.getModel());
		List<VersionDomain> list = versionMapper.loadDynamic(params);

		if (list == null || list.size() == 0)
			throw new ObjectNotFoundException(VersionDomain.class);

		/**
		 * 按Id倒排序
		 */
		Collections.sort(list, new Comparator<VersionDomain>() {
			@Override
			public int compare(VersionDomain o1, VersionDomain o2) {
				if (o1.getId() < o2.getId())
					return 1;
				else if (o1.getId() == o2.getId())
					return 0;
				else
					return -1;
			}
		});

		QueryVersionResponse queryVersionResponse = new QueryVersionResponse();

		queryVersionResponse.setLatestVersion(list.get(0).getVersion());
		queryVersionResponse.setDesc(list.get(0).getDescription());
		queryVersionResponse.setForced(list.get(0).getForced());

		queryVersionResponse.setDownloadUrl("下载地址这么拼：主机地址(http://211.157.19.83)" + "/download/android/版本号/taw.apk, " + //
				"把更新包(taw.apk)放在211.157.19.83的/sharecentos7.1/download/android/版本号/taw.apk处");

		return queryVersionResponse;

	}

}
