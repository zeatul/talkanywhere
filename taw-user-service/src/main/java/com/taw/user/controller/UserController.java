package com.taw.user.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taw.user.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	/**
	 * hello 测试用
	 * @param locale
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/user/hello.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String helloWorld(Locale locale, Model model) throws IOException {
		model.addAttribute("msg", "/user/hello.do");
		return "success";
	}

	/**
	 * 新建用户
	 * @param locale
	 * @param model
	 * @param request
	 */
	@RequestMapping(value = "/user/create.do", method = RequestMethod.POST)
	public void createUser(Locale locale, Model model, HttpServletRequest request) {
		
	}

}
