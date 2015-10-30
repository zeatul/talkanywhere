package com.taw.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.pub.enums.EnumBoolean;
import com.hawk.pub.pkgen.PkGenerator;
import com.hawk.utility.DateTools;
import com.hawk.utility.DomainTools;
import com.hawk.utility.StringTools;
import com.hawk.utility.check.CheckTools;
import com.hawk.utility.security.MD5Tools;
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
	 * 产生密码的md5签名
	 * @param password
	 * @return
	 */
	public String signedPassword(String password){
		return MD5Tools.sign(password+userServiceConfigure.getPwdMd5Key());
	}
	
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
		userDomain.setPassword(signedPassword(createUserParam.getPassword()));
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
	
	
	public UserDomain queryUser(String mobile){
		if (StringTools.isNullOrEmpty(mobile))
			return null;
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("mobile", mobile);
		List<UserDomain> list = userMapper.loadUserDynamic(params);
		if (list.size() == 0)
			return null;
		UserDomain userDomain = list.get(0);
		return userDomain;
	}

}
