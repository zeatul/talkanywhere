package com.taw.scene.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import com.taw.pub.scene.request.ChangeOnlineCountParam;
import com.taw.pub.scene.request.EnterSceneParam;
import com.taw.pub.scene.request.LeaveSceneParam;
import com.taw.pub.scene.request.QuerySceneByNameParam;
import com.taw.pub.scene.request.QuerySceneInRegionParam;
import com.taw.pub.scene.request.QuerySingleSceneParam;
import com.taw.pub.scene.request.QueryUsersOnSceneParam;
import com.taw.pub.scene.request.QueryUsersOnlineSceneParam;
import com.taw.pub.scene.response.EnterSceneResp;
import com.taw.pub.scene.response.SceneResp;
import com.taw.scene.domain.BookmarkDomain;
import com.taw.scene.service.SceneService;
import com.taw.user.auth.AuthThreadLocal;

@Controller
public class SceneController {
	
	@Autowired
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
		model.addAttribute("msg", "/scene/hello.do");
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
	@RequestMapping(value = "/scene/region/search.do", method = RequestMethod.POST)
	public void search(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		QuerySceneInRegionParam querySceneInRegionParam = HttpRequestHandler.handle(request, QuerySceneInRegionParam.class);		
		
		HttpResponseHandler.handle(response, SuccessResponse.build(sceneService.query(querySceneInRegionParam)));
	}
	
	/**
	 * 查询指定区域范围内的场景
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/scene/name/search.do", method = RequestMethod.POST)
	public void searchByName(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		QuerySceneByNameParam querySceneByNameParam = HttpRequestHandler.handle(request, QuerySceneByNameParam.class);
		
		
		List<SceneResp> sceneRespList = sceneService.query(querySceneByNameParam);
		
		HttpResponseHandler.handle(response, SuccessResponse.build(sceneRespList));
	}
	
	/**
	 * 查询指定场景的信息
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/scene/info.do", method = RequestMethod.POST)
	public void info(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		QuerySingleSceneParam param = HttpRequestHandler.handle(request, QuerySingleSceneParam.class);
		
		Long userId = AuthThreadLocal.getUserId();
		Map<Long, BookmarkDomain> bookedSceneIdMap = null;
		if (userId != null) {
			bookedSceneIdMap = sceneService.bookedSceneIdMap(userId);			
		}
		
		SceneResp sceneResp = sceneService.querySingleScene(param,bookedSceneIdMap);
		
		HttpResponseHandler.handle(response, SuccessResponse.build(sceneResp));
	}
	
	/**
	 * 进入指定场景
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/scene/enter.do", method = RequestMethod.POST)
	public void enter(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		EnterSceneParam enterSceneParam = HttpRequestHandler.handle(request, EnterSceneParam.class);
		enterSceneParam.setUserId(AuthThreadLocal.getUserId());
		enterSceneParam.setToken(AuthThreadLocal.getToken());
		EnterSceneResp enterSceneResp = sceneService.enterScene(enterSceneParam);
		HttpResponseHandler.handle(response, SuccessResponse.build(enterSceneResp));
		
	}
	
	/**
	 * 离开指定场景
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/scene/leave.do", method = RequestMethod.POST)
	public void leave(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		LeaveSceneParam leaveSceneParam = HttpRequestHandler.handle(request, LeaveSceneParam.class);
		leaveSceneParam.setUserId(AuthThreadLocal.getUserId());
		leaveSceneParam.setToken(AuthThreadLocal.getToken());
		sceneService.leaveScene(leaveSceneParam);
		HttpResponseHandler.handle(response, SuccessResponse.SUCCESS_RESPONSE);
	}
	
	/**
	 * 注册用户，修改现场人数
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/scene/online/change.do", method = RequestMethod.POST)
	public void changeOnlineCount(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ChangeOnlineCountParam changeOnlineCountParam = HttpRequestHandler.handle(request, ChangeOnlineCountParam.class); 
		changeOnlineCountParam.setUserId(AuthThreadLocal.getUserId());
		changeOnlineCountParam.setToken(AuthThreadLocal.getToken());
		sceneService.ChangeOnlineCount(changeOnlineCountParam);
		HttpResponseHandler.handle(response, SuccessResponse.SUCCESS_RESPONSE);
	}
	
	/**
	 * 游客，修改现场人数
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/scene/online/change2.do", method = RequestMethod.POST)
	public void changeOnlineCount2(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ChangeOnlineCountParam changeOnlineCountParam = HttpRequestHandler.handle(request, ChangeOnlineCountParam.class); 
		sceneService.ChangeOnlineCount(changeOnlineCountParam);
		HttpResponseHandler.handle(response, SuccessResponse.SUCCESS_RESPONSE);
	}
	
	@RequestMapping(value = "/scene/online/users.do", method = RequestMethod.POST)
	public void queryUsersOnlineScene(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		QueryUsersOnlineSceneParam queryUsersOnlineSceneParam = HttpRequestHandler.handle(request, QueryUsersOnlineSceneParam.class); 
		
		HttpResponseHandler.handle(response, SuccessResponse.build(sceneService.queryUsersOnlineScene(queryUsersOnlineSceneParam)));
	}
	
	@RequestMapping(value = "/scene/users.do", method = RequestMethod.POST)
	public void queryUsersOnScene(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		QueryUsersOnSceneParam queryUsersOnSceneParam = HttpRequestHandler.handle(request, QueryUsersOnSceneParam.class); 
		
		HttpResponseHandler.handle(response, SuccessResponse.build(sceneService.queryUsersOnScene(queryUsersOnSceneParam)));
	}
	
//	待测试
//	@RequestMapping(value = "/scene/fuzzied.do", method = RequestMethod.POST)
//	public void testQueryFuzziedScene(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
//		HttpResponseHandler.handle(response, SuccessResponse.build(sceneService.testFuzzied()));
//	}

}
