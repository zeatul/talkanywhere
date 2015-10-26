package com.taw.user.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	/**
	 * hello 测试用
	 * @param locale
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/user/hello.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String helloWorldDelete(Locale locale, Model model) throws IOException {
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
