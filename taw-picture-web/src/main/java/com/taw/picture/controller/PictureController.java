package com.taw.picture.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hawk.pub.web.HttpRequestHandler;
import com.hawk.pub.web.HttpResponseHandler;
import com.hawk.pub.web.SuccessResponse;
import com.taw.picture.configure.PictureServiceConfigure;
import com.taw.picture.service.PictureService;
import com.taw.pub.picture.request.AddCommentParam;
import com.taw.pub.picture.request.PictureInfoParam;
import com.taw.pub.picture.request.PicturePathParam;
import com.taw.pub.picture.request.RemoveCommentParam;
import com.taw.pub.picture.request.SearchCommentParam;
import com.taw.pub.picture.request.SearchGlobalHotPictureParam;
import com.taw.pub.picture.request.SearchSceneHotPictureParam;
import com.taw.pub.picture.request.ThumbPictureParam;
import com.taw.pub.picture.response.AddCommentResp;
import com.taw.pub.picture.response.PictureCommentInfoResp;
import com.taw.pub.picture.response.PictureInfoResp;
import com.taw.pub.picture.response.PictureStatResp;
import com.taw.pub.picture.response.PicturePathResp;
import com.taw.user.auth.AuthThreadLocal;

@Controller
public class PictureController {
	
	@Autowired
	private PictureService pictureService;
	
	@Autowired
	@Qualifier("pictureServiceConfigure")
	private PictureServiceConfigure pictureServiceConfigure; 
	
	
	/**
	 * hello 测试用
	 * @param locale
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/pic/hello.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String helloWorld(Locale locale, Model model) throws IOException {
		model.addAttribute("msg", "/pic/hello.do");
		return "success";
	}
	
	/**
	 * 点赞
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/pic/thumb.do", method = RequestMethod.POST)
	public void thumb(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ThumbPictureParam thumbPictureParam = HttpRequestHandler.handle(request, ThumbPictureParam.class);
		thumbPictureParam.setUserId(AuthThreadLocal.getUserId());
		
		PictureStatResp pictureInfoResp = pictureService.thumbPicture(thumbPictureParam);
		HttpResponseHandler.handle(response, SuccessResponse.build(pictureInfoResp));
	}
	
	
	/**
	 * 返回图片的相对路径 ,给前端拼接图片路径用
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/pic/path.do", method = RequestMethod.POST)
	public void path(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		PicturePathParam picturePathParam = HttpRequestHandler.handle(request, PicturePathParam.class);
		String path = pictureService.computeDir(picturePathParam.getUuid()).getPath();
		PicturePathResp picturePathResp = new PicturePathResp();
		picturePathResp.setUrl(pictureServiceConfigure.getUrlHead()+path+"/"+picturePathParam.getUuid());
		picturePathResp.setSurl(pictureServiceConfigure.getSurlHead()+path+"/"+picturePathParam.getUuid());
		HttpResponseHandler.handle(response, SuccessResponse.build(picturePathResp));
	}
	
	/**
	 * 查询图片信息
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/pic/info.do", method = RequestMethod.POST)
	public void info(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		PictureInfoParam pictureInfoParam = HttpRequestHandler.handle(request, PictureInfoParam.class);		
		PictureInfoResp pictureInfoResp = pictureService.info(pictureInfoParam);
		HttpResponseHandler.handle(response, SuccessResponse.build(pictureInfoResp));
	}
	
	/**
	 * 列出评论
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/pic/comment/search.do", method = RequestMethod.POST)
	public void searchComment(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		SearchCommentParam searchCommentParam = HttpRequestHandler.handle(request, SearchCommentParam.class);
		List<PictureCommentInfoResp> list = pictureService.searchComment(searchCommentParam);
		HttpResponseHandler.handle(response, SuccessResponse.build(list));
	}
	
	/**
	 * 添加评论
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/pic/comment/add.do", method = RequestMethod.POST)
	public void addComment(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		AddCommentParam addCommentParam = HttpRequestHandler.handle(request, AddCommentParam.class);
		addCommentParam.setUserId(AuthThreadLocal.getUserId());
		AddCommentResp addCommentResp =  pictureService.addComment(addCommentParam);
		HttpResponseHandler.handle(response, SuccessResponse.build(addCommentResp));
	}
	
	/**
	 * 删除评论
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/pic/comment/remove.do", method = RequestMethod.POST)
	public void removeComment(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		RemoveCommentParam removeCommentParam = HttpRequestHandler.handle(request, RemoveCommentParam.class);
		removeCommentParam.setUserId(AuthThreadLocal.getUserId());
		PictureStatResp removeCommentResp =  pictureService.removeComment(removeCommentParam);
		HttpResponseHandler.handle(response, SuccessResponse.build(removeCommentResp));
	}
	
	/**
	 * 全球热门图片
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/pic/global/hot.do", method = {RequestMethod.POST,RequestMethod.GET})
	public void globalHot(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		SearchGlobalHotPictureParam searchGlobalHotPictureParam = HttpRequestHandler.handle(request, SearchGlobalHotPictureParam.class);
		HttpResponseHandler.handle(response, SuccessResponse.build(pictureService.loadGlobalHotPicture(searchGlobalHotPictureParam)));
	}
	
	
	/**
	 * 场景热门图片
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/pic/scene/hot.do", method = {RequestMethod.POST,RequestMethod.GET})
	public void sceneHot(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		SearchSceneHotPictureParam sceneHotPictureParam = HttpRequestHandler.handle(request, SearchSceneHotPictureParam.class);
		HttpResponseHandler.handle(response, SuccessResponse.build(pictureService.loadSceneHotPicture(sceneHotPictureParam)));
		
	}

}
