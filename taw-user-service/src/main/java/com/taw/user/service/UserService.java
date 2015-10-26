package com.taw.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.utility.DomainTools;
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
	 */
	public void createUser(CreateUserParam createUserParam){
		/**
		 * 一般校验
		 */
		
		/**
		 * 转换为domain
		 */
		UserDomain userDomain = new UserDomain();
		DomainTools.copy(createUserParam, userDomain);
		
		/**
		 * 赋特殊固定值
		 */
		
		/**
		 * 入库
		 */
	}

}
