package com.taw.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.utility.DateTools;
import com.hawk.utility.DomainTools;
import com.hawk.utility.check.CheckTools;
import com.taw.pub.user.request.CreateUserParam;
import com.taw.user.domain.UserDomain;
import com.taw.user.mapper.UserMapper;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 创建新用户
	 * @param createUserParam
	 * @throws Exception 
	 */
	public void createUser(CreateUserParam createUserParam) throws Exception{
		/**
		 * 一般校验
		 */
		CheckTools.check(createUserParam);
		
		/**
		 * 转换为domain
		 */
		UserDomain userDomain = new UserDomain();
		DomainTools.copy(createUserParam, userDomain);
		
		/**
		 * 赋特殊固定值
		 */
		userDomain.setCrdt(DateTools.now());
		userDomain.setUpdt(userDomain.getCrdt());
		
		/**
		 * 入库
		 */
		userMapper.insert(userDomain);
	}

}
