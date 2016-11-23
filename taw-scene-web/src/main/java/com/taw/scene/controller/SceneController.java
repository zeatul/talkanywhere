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
import com.hawk.utility.check.CheckTools;
import com.taw.pub.scene.request.ChangePresentParam;
import com.taw.pub.scene.request.EnterSceneParam;
import com.taw.pub.scene.request.ExistFootPrintParam;
import com.taw.pub.scene.request.LeaveSceneParam;
import com.taw.pub.scene.request.QuerySceneByNameParam;
import com.taw.pub.scene.request.QuerySceneInRegionParam;
import com.taw.pub.scene.request.QuerySingleSceneParam;
import com.taw.pub.scene.request.QueryEnteredUsersOfSceneParam;
import com.taw.pub.scene.request.QueryPresentUsersOfSceneParam;
import com.taw.pub.scene.response.EnterSceneResp;
import com.taw.pub.scene.response.ExistFootPrintResp;
import com.taw.pub.scene.response.SceneResp;
import com.taw.scene.domain.BookmarkDomain;
import com.taw.scene.service.FootPrintService;
import com.taw.scene.service.SceneService;
import com.taw.user.auth.AuthThreadLocal;

@Controller
public class SceneController {
	
	@Autowired
	private SceneService sceneService;
	@Autowired
	private FootPrintService footPrintService;
	
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
	 * 注册用户，修改在现场人数
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/scene/present/change.do", method = RequestMethod.POST)
	public void changePresent(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ChangePresentParam changePresentParam = HttpRequestHandler.handle(request, ChangePresentParam.class); 
		changePresentParam.setUserId(AuthThreadLocal.getUserId());
		sceneService.changePresent(changePresentParam);
		HttpResponseHandler.handle(response, SuccessResponse.SUCCESS_RESPONSE);
	}
	
	@RequestMapping(value = "/scene/present/exist.do", method = RequestMethod.POST)
	public void isPresentedInScene(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ExistFootPrintParam existFootPrintParam = HttpRequestHandler.handle(request, ExistFootPrintParam.class); 
		CheckTools.check(existFootPrintParam);
		
		ExistFootPrintResp existFootPrintResp = new ExistFootPrintResp();		
		
		
		if (sceneService.isPresentedInScene(existFootPrintParam)){
			existFootPrintResp.setExist("1");
		}else{
			existFootPrintResp.setExist("0");
		}
		
		HttpResponseHandler.handle(response, SuccessResponse.build(existFootPrintResp));
	}
	
	
	
	@RequestMapping(value = "/scene/present/users.do", method = RequestMethod.POST)
	public void queryPresentUsersOfScene(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		QueryPresentUsersOfSceneParam queryPresentUsersOfSceneParam = HttpRequestHandler.handle(request, QueryPresentUsersOfSceneParam.class); 
		
		HttpResponseHandler.handle(response, SuccessResponse.build(sceneService.queryPresentUsersOfScene(queryPresentUsersOfSceneParam)));
	}
	
	@RequestMapping(value = "/scene/enter/users.do", method = RequestMethod.POST)
	public void queryEnteredUsersOfScene(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		QueryEnteredUsersOfSceneParam queryEnteredUsersOfSceneParam = HttpRequestHandler.handle(request, QueryEnteredUsersOfSceneParam.class); 
		
		HttpResponseHandler.handle(response, SuccessResponse.build(sceneService.queryEnteredUsersOfScene(queryEnteredUsersOfSceneParam)));
	}
	
	@RequestMapping(value = "/scene/enter/exist.do", method = RequestMethod.POST)
	public void exist(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ExistFootPrintParam existFootPrintParam = HttpRequestHandler.handle(request, ExistFootPrintParam.class); 
		
		ExistFootPrintResp existFootPrintResp = new ExistFootPrintResp();
		
		if (footPrintService.hasEnteredScene(existFootPrintParam)){
			existFootPrintResp.setExist("1");
		}else{
			existFootPrintResp.setExist("0");
		}
		
		HttpResponseHandler.handle(response, SuccessResponse.build(existFootPrintResp));
	}
	
//	待测试
//	@RequestMapping(value = "/scene/fuzzied.do", method = RequestMethod.POST)
//	public void testQueryFuzziedScene(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
//		HttpResponseHandler.handle(response, SuccessResponse.build(sceneService.testFuzzied()));
//	}

}
