package com.taw.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hawk.utility.JsonTools;
import com.taw.pub.user.enums.EnumContactType;
import com.taw.pub.user.request.AddUserContactParam;
import com.taw.pub.user.request.AddUserContactParam.CoUser;
import com.taw.pub.user.request.RemoveUserContactParam;

public class UserContactControllerTest extends AbstractControllerTest {

	public UserContactControllerTest() throws Exception {
		super();		
	}
	
//	@Test
	public void testAdd() throws Exception{
		String path = contextPath + "/user/contact/add.do";
		
		
		List<CoUser> coUsers = new ArrayList<CoUser>();
		CoUser coUser = new CoUser();
		coUser.setCoUserId(37l);
		coUser.setRemark("hello2");
		coUsers.add(coUser);
		coUser = new CoUser();
		coUser.setCoUserId(31l);
		coUser.setRemark("hello3");
		coUsers.add(coUser);
		
		AddUserContactParam param = new AddUserContactParam();
		
		param.setContactType(EnumContactType.BLACKLIST.toString());
		param.setCoUsers(coUsers);
		
		String content = JsonTools.toJsonString(param);

		printSend(content);

		String result = httpClientHelper.post(path, content, genAuthMap());

		printResult(result);
	}
	
//	@Test
	public void testSearch() throws Exception{
		String path = contextPath + "/user/contact/search.do";
		

		String result = httpClientHelper.post(path, "", genAuthMap());

		printResult(result);
	}
	
	@Test
	public void testRemove() throws Exception{
		String path = contextPath + "/user/contact/remove.do";
		RemoveUserContactParam param = new RemoveUserContactParam();
		List<Long> coUserIds = new ArrayList<Long>();
		coUserIds.add(37l);
		coUserIds.add(31l);
		param.setCoUserIds(coUserIds);
		String content = JsonTools.toJsonString(param);

		printSend(content);

		String result = httpClientHelper.post(path, content, genAuthMap());

		printResult(result);
	}

}
