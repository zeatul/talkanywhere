package com.taw.picture.controller;

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
import com.taw.picture.mapper.PictureMapper;
import com.taw.picture.service.PictureService;
import com.taw.pub.picture.request.AddCommentParam;
import com.taw.pub.picture.request.PictureInfoParam;
import com.taw.pub.picture.request.RemoveCommentParam;
import com.taw.pub.picture.request.SearchCommentParam;
import com.taw.pub.picture.request.ThumbPictureParam;
import com.taw.pub.picture.response.AddCommentResp;
import com.taw.pub.picture.response.PictureCommentInfoResp;
import com.taw.pub.picture.response.PictureInfoResp;
import com.taw.pub.picture.response.RemoveCommentResp;
import com.taw.user.auth.AuthThreadLocal;

@Controller
public class PictureController {
	
	@Autowired
	private PictureService pictureService;
	
	
	
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
		
		PictureInfoResp pictureInfoResp = pictureService.thumbPicture(thumbPictureParam);
		HttpResponseHandler.handle(response, SuccessResponse.build(pictureInfoResp));
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
		RemoveCommentResp removeCommentResp =  pictureService.removeComment(removeCommentParam);
		HttpResponseHandler.handle(response, SuccessResponse.build(removeCommentResp));
	}
	
	

}
