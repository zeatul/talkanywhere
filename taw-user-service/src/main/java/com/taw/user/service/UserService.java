package com.taw.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.utility.DateTools;
import com.hawk.utility.DomainTools;
import com.hawk.utility.check.CheckTools;
import com.hawk.utility.security.MD5Tools;
import com.taw.pub.enums.EnumBoolean;
import com.taw.pub.pkgen.PkGenerator;
import com.taw.pub.user.enums.EnumUserKind;
import com.taw.pub.user.enums.EnumUserStatus;
import com.taw.pub.user.request.CreateUserParam;
import com.taw.user.configure.UserServiceConfigure;
import com.taw.user.domain.UserDomain;
import com.taw.user.mapper.UserMapper;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserServiceConfigure userServiceConfigure;
	
	/**
	 * 创建新用户
	 * @param createUserParam
	 * @throws Exception 
	 */
	public UserDomain createUser(CreateUserParam createUserParam) throws Exception{
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
		userDomain.setPassword(MD5Tools.sign(createUserParam.getPassword()+userServiceConfigure.getPwdMd5Key() ));
		userDomain.setChcked(EnumBoolean.TRUE.getValue());
		userDomain.setMobileKind(EnumBoolean.FALSE.getValue());
		userDomain.setStatus(EnumUserStatus.VALID.toString());
		userDomain.setKind(EnumUserKind.NORMAL.toString());
		userDomain.setCrdt(DateTools.now());
		userDomain.setUpdt(userDomain.getCrdt());
		
		userDomain.setId(PkGenerator.genPk());
		
		/**
		 * 入库
		 */
		int rowCount = userMapper.insert(userDomain);
		
		if (rowCount != 1)
			throw new RuntimeException("Failed to insert userDomain");
		
		return userDomain;
	}

}
