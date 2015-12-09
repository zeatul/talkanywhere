package com.taw.user.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.hawk.pub.pkgen.PkGenerator;
import com.hawk.utility.DateTools;
import com.hawk.utility.check.CheckTools;
import com.taw.pub.user.request.AddUserContactParam;
import com.taw.pub.user.request.AddUserContactParam.CoUser;
import com.taw.pub.user.request.RemoveUserContactParam;
import com.taw.pub.user.request.SearchUserContactParam;
import com.taw.user.domain.UserContactDomain;
import com.taw.user.mapper.UserContactMapper;

@Service
public class UserContactService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserContactMapper userContactMapper;
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * 新增用户关系
	 * @param addUserContactParam
	 * @throws Exception
	 */
	public void add(AddUserContactParam addUserContactParam ) throws Exception{
		CheckTools.check(addUserContactParam);
		for (int i=0; i< addUserContactParam.getCoUsers().size(); i++){
			CheckTools.check(addUserContactParam.getCoUsers().get(i));
		}
		Long userId = addUserContactParam.getUserId();
		String contactType = addUserContactParam.getContactType();
		Date now = DateTools.now();
		for (CoUser coUser : addUserContactParam.getCoUsers()){
			try {
				
				/*不能加自身*/
				if (userId.equals(coUser.getCoUserId()))
					continue;
				
				if (userService.loadUser(coUser.getCoUserId(), true) == null)
					continue;
				
				UserContactDomain userContactDomain = new UserContactDomain();			
				userContactDomain.setUserId(userId);
				userContactDomain.setCoUserId(coUser.getCoUserId());
				userContactDomain.setRemark(coUser.getRemark());
				userContactDomain.setType(contactType);
				userContactDomain.setCrdt(now);
				userContactDomain.setUpdt(now);
				userContactDomain.setId(PkGenerator.genPk());			
				userContactMapper.insert(userContactDomain);
			} catch (DuplicateKeyException e) {
				logger.error("primay key conflicat,userId="+userId+",coUserId"+coUser.getCoUserId(),e);
			}
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
	
	public List<UserContactDomain> search(SearchUserContactParam searchUserContactParam) throws Exception{
		CheckTools.check(searchUserContactParam);
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("userId", searchUserContactParam.getUserId());
		return userContactMapper.loadDynamic(params);
	}

}
