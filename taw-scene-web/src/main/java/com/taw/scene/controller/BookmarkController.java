package com.taw.scene.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hawk.pub.web.HttpRequestHandler;
import com.hawk.pub.web.HttpResponseHandler;
import com.hawk.pub.web.SuccessResponse;
import com.hawk.utility.DomainTools;
import com.taw.pub.scene.request.AddBookmarkParam;
import com.taw.pub.scene.request.QueryBookmarkParam;
import com.taw.pub.scene.request.RemoveBookmarkParam;
import com.taw.pub.scene.response.BookmarkResp;
import com.taw.scene.domain.BookmarkDomain;
import com.taw.scene.service.BookmarkService;
import com.taw.user.auth.AuthThreadLocal;


@Controller
public class BookmarkController {
	
	@Autowired
	private BookmarkService bookmarkService;
	
	/**
	 * hello 测试用
	 * @param locale
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/scene/bookmark/hello.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String helloWorld(Locale locale, Model model) throws IOException {
		model.addAttribute("msg", "/scene/bookmark/hello.do");
		return "success";
	}
	
	
	/**
	 * 查询指定区域范围内的场景
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/scene/bookmark/add.do", method = RequestMethod.POST)
	public void add(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		AddBookmarkParam addBookmarkParam = HttpRequestHandler.handle(request, AddBookmarkParam.class);
		addBookmarkParam.setUserId(AuthThreadLocal.getUserId());
		;		
		/**
		 * 返回结果
		 */		
		HttpResponseHandler.handle(response, SuccessResponse.build(bookmarkService.add(addBookmarkParam)));
	}
	
	/**
	 * 查询
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/scene/bookmark/search.do", method = {RequestMethod.POST ,RequestMethod.GET })
	public void search(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		QueryBookmarkParam queryBookmarkParam = HttpRequestHandler.handle(request, QueryBookmarkParam.class);
		queryBookmarkParam.setUserId(AuthThreadLocal.getUserId());
		List<BookmarkDomain> list =  bookmarkService.search(queryBookmarkParam);
		
		List<BookmarkResp> result = new ArrayList<BookmarkResp>();
		
		DomainTools.copy(list, result, BookmarkResp.class);
		
		HttpResponseHandler.handle(response, SuccessResponse.build(result));
	}
	
	/**
	 * 删除
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/scene/bookmark/remove.do", method = {RequestMethod.POST ,RequestMethod.GET })
	public void remove(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		RemoveBookmarkParam removeBookmarkParam = HttpRequestHandler.handle(request, RemoveBookmarkParam.class);
		removeBookmarkParam.setUserId(AuthThreadLocal.getUserId());
		bookmarkService.remove(removeBookmarkParam);
		HttpResponseHandler.handle(response, SuccessResponse.SUCCESS_RESPONSE);
	}
	
	

}
