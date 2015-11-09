package com.taw.user.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.pub.pkgen.PkGenerator;
import com.hawk.utility.DateTools;
import com.hawk.utility.check.CheckTools;
import com.taw.pub.user.request.AddUserContactParam;
import com.taw.pub.user.request.RemoveUserContactParam;
import com.taw.user.domain.UserContactDomain;
import com.taw.user.mapper.UserContactMapper;

@Service
public class UserContactService {
	
	@Autowired
	private UserContactMapper userContactMapper;
	
	
	/**
	 * 新增用户关系
	 * @param addUserContactParam
	 * @throws Exception
	 */
	public void add(AddUserContactParam addUserContactParam ) throws Exception{
		CheckTools.check(addUserContactParam);
		Long userId = addUserContactParam.getUserId();
		String contactType = addUserContactParam.getContactType();
		Date now = DateTools.now();
		for (Long coUserId : addUserContactParam.getCoUserIds()){
			UserContactDomain userContactDomain = new UserContactDomain();
			
			userContactDomain.setUserId(userId);
			userContactDomain.setCoUserId(coUserId);
			userContactDomain.setType(contactType);
			userContactDomain.setCrdt(now);
			userContactDomain.setUpdt(now);
			userContactDomain.setId(PkGenerator.genPk());
			
			userContactMapper.insert(userContactDomain);
		}
	}
	
	
	private Map<String,Object> genDelParam(Long userId, Long coUserId){
		Map<String,Object> params = new HashMap<String, Object>();
		
		if (coUserId == null || userId == null)
			throw new RuntimeException("The userId or coUserId can't be null");
		
		params.put("userId", userId);
		params.put("coUserId", coUserId);
		return params;
	}
	
	/**
	 * 删除用户关系
	 * @param removeUserContactParam
	 * @throws Exception
	 */
	public void remove(RemoveUserContactParam removeUserContactParam) throws Exception{
		CheckTools.check(removeUserContactParam);
		Long userId = removeUserContactParam.getUserId();
		for (Long coUserId : removeUserContactParam.getCoUserIds()){
			userContactMapper.deleteDynamic(genDelParam(userId,coUserId));
		}
	}

}
