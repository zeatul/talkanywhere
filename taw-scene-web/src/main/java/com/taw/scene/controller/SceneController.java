package com.taw.scene.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hawk.pub.web.HttpRequestHandler;
import com.hawk.pub.web.HttpResponseHandler;
import com.hawk.pub.web.SuccessResponse;
import com.taw.pub.scene.request.QuerySceneInRegionParam;
import com.taw.pub.scene.response.SceneResp;
import com.taw.pub.user.request.LoginParam;
import com.taw.pub.user.response.LoginResp;
import com.taw.scene.service.SceneService;
import com.taw.user.service.LoginService.LoginInfo;

@Controller
public class SceneController {
	
	private SceneService sceneService;
	
	/**
	 * hello 测试用
	 * @param locale
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/scene/hello.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String helloWorld(Locale locale, Model model) throws IOException {
		model.addAttribute("msg", "/user/contact/hello.do");
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
	@RequestMapping(value = "/scene/search.do", method = RequestMethod.POST)
	public void search(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		QuerySceneInRegionParam querySceneInRegionParam = HttpRequestHandler.handle(request, QuerySceneInRegionParam.class);
		
		List<SceneResp> list =  sceneService.queryForWeb(querySceneInRegionParam);
		
		/**
		 * 返回结果
		 */
		
		HttpResponseHandler.handle(response, SuccessResponse.build(list));
	}
	
	@RequestMapping(value = "/scene/footprint/search.do", method = RequestMethod.POST)
	public void footprint(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response){
		
	}

}
