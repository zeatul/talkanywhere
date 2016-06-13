package com.taw.picture.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.hawk.pub.web.HttpRequestHandler;
import com.hawk.pub.web.HttpResponseHandler;
import com.hawk.pub.web.SuccessResponse;
import com.hawk.utility.check.CheckTools;
import com.taw.picture.configure.PictureServiceConfigure;
import com.taw.picture.exception.PictureNotFoundException;
import com.taw.picture.service.PictureService;
import com.taw.picture.service.PictureService.ComputeResult;
import com.taw.pub.scene.request.UploadLengthParam;
import com.taw.pub.scene.response.UploadLengthResponse;

@Controller
public class FileUploadController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("pictureServiceConfigure")
	private PictureServiceConfigure pictureServiceConfigure;

	@Autowired
	private PictureService pictureService;

	/**
	 * hello 测试用
	 * 
	 * @param locale
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/pic/upload/hello.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String helloWorld(Locale locale, Model model) throws IOException {
		model.addAttribute("msg", "/file/upload/hello.do");
		return "success";
	}

	/**
	 * 上传文件， uuid = yyyyMMddHHmmss +"_"+ 32位uuid+ .suffix ，必填 srcFile =
	 * 上传的本地源文件名称 ，选填，需要encodeUrl srcFileSize = 上传的本地源文件实际大小 ，必填 byteArraySize =
	 * 本次上传的byte数组大小 offset = 本次上传内容 在文件的起始位置 上传逻辑，
	 * 文件可以一次性传完，也可以分段上传，但必须按顺序来，系统检测到文件已经上传的内容和 srcFileSize相等，则认为本次文件全部上传完成
	 * 上传的文件体，作为一个 byte数组，放在http 的 body 里。
	 * 如果上传过程断网，则客户单先调用/file/length.do获取已经上传的文件大小，然后续传。
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/pic/upload.do", method = RequestMethod.POST)
	public void uploadFile(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/**
		 * yyyyMMddHHmmss+"_"+ 32位uuid+ .suffix
		 */
		String uuid = request.getParameter("uuid");
		ComputeResult computeResult = pictureService.computeDir(uuid);
		String dir = computeResult.getDir();
		// String suffix = computeResult.getSuffix();
		/**
		 * 上传的本地源文件名称
		 */
		String srcFile = request.getParameter("srcFile");

		/**
		 * 上传的本地源文件实际大小
		 */
		String srcFileSizeStr = request.getParameter("srcFileSize");
		if (StringUtils.isEmpty(srcFileSizeStr)) {
			throw new RuntimeException("srcFileSize is empty");
		}
		long srcFileSize = new Long(srcFileSizeStr);
		/**
		 * 本次上传的byte数组大小
		 */
		String byteArraySizeStr = request.getParameter("byteArraySize");
		if (StringUtils.isEmpty(byteArraySizeStr)) {
			throw new RuntimeException("byteArraySize is empty");
		}
		long byteArraySize = new Long(byteArraySizeStr);

		/**
		 * 本次上传内容 在文件的起始位置
		 */
		String offsetStr = request.getParameter("offset");
		if (StringUtils.isEmpty(byteArraySizeStr)) {
			throw new RuntimeException("offset is empty");
		}
		long offset = new Long(offsetStr);
		if (offset > srcFileSize)
			throw new RuntimeException("offset is wrong");

		logger.info("upload file, uuid={},srcFile={},srcFileSize={},byteArraySize={},offset={}", uuid, srcFile, srcFileSize, byteArraySize, offset);

		InputStream in = request.getInputStream();
		mkdir(dir);
		String filePath = dir + File.separator + uuid;
		File file = new File(filePath + ".tmp");

		long maxSize = pictureServiceConfigure.getAllowFileSize();
		if (srcFileSize > maxSize || file.length() > maxSize) {
			if (file.exists()) {
				file.delete();
			}
			throw new RuntimeException("The upload file size shouln't be over " + maxSize);
		}

		FileOutputStream fos = null;
		try {
			if (file.exists() && file.length() >= srcFileSize) {
				logger.info("Success to upload file = {}", file.getName());
			} else {
				fos = new FileOutputStream(file, true);
				IOUtils.copyLarge(in, fos);
			}
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (Exception e) {
				logger.error("failed to close file");
			}

			if (file.exists() && file.length() > pictureServiceConfigure.getAllowFileSize()) {
				file.delete();
				throw new RuntimeException("The upload file size shouln't be over " + maxSize);
			}
		}

		if (file.length() >= srcFileSize) {
			logger.info("Success to upload file = {}", file.getName());
			boolean flag = file.renameTo(new File(filePath));

			/**
			 * 创建一个success 后缀的 flag 文件，仅仅标识文件已经上传完成。文件入库后，删除.success文件
			 */
			File successFile = new File(filePath + ".success");
			successFile.createNewFile();
			if (!flag)
				throw new RuntimeException("Failed to rename file");
			else
				logger.info("Success to rename file = {}", file.getName());
		}

		HttpResponseHandler.handle(response, SuccessResponse.SUCCESS_RESPONSE);
	}

	/**
	 * 获取已经上传文件的内容大小，续传时候，可以从这个位置开始继续上传。
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/pic/length.do", method = RequestMethod.POST)
	public void uploadLength(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UploadLengthParam param = HttpRequestHandler.handle(request, UploadLengthParam.class);
		CheckTools.check(param);

		String uuid = param.getUuid();
		String filePath = pictureService.computeDir(uuid).getDir() + File.separator + uuid;
		File file = new File(filePath + ".tmp");
		long size = 0;
		if (file.exists()) {
			size = file.length();
		} else {
			file = new File(filePath);
			if (file.exists()) {
				size = file.length();
			} else {
				throw new PictureNotFoundException();
			}
		}
		UploadLengthResponse uploadLengthResponse = new UploadLengthResponse();
		uploadLengthResponse.setSize(size);

		HttpResponseHandler.handle(response, SuccessResponse.build(uploadLengthResponse));
	}

	private void mkdir(String path) {
		;
		File f = new File(path);
		if (!f.exists()) {
			if (!f.mkdirs()) {
				if (!f.exists())
					throw new RuntimeException("Failed to mkdir " + path);
			} else {
				f.setReadable(true);
				f.setWritable(true);
			}
		}
	}

	@RequestMapping(value = "/pic/upload2.do", method = RequestMethod.POST)
	public void uploadFile2(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/**
		 * yyyyMMddHHmmss+"_"+ 32位uuid+ .suffix
		 */
		String uuid = request.getParameter("uuid");
		ComputeResult computeResult = pictureService.computeDir(uuid);
		String dir = computeResult.getDir();

		
		mkdir(dir);
		String filePath = dir + File.separator + uuid;
		File file = new File(filePath + ".tmp"); // 目标保存文件名

		// 创建一个通用的多部分解析器.
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 设置编码
		commonsMultipartResolver.setDefaultEncoding("utf-8");
		//判断 request 是否有文件上传,即多部分请求...
		if (commonsMultipartResolver.isMultipart(request)) {
			//转换成多部分request
			MultipartHttpServletRequest multipartRequest = commonsMultipartResolver.resolveMultipart(request);
			// file 是指 文件上传标签的 name=值  
		    // 根据 name 获取上传的文件...  
		    MultipartFile multipartFile = multipartRequest.getFile("file");  
		    if (multipartFile == null)
		    	throw new Exception("the file element tag should be 'file' ");
		    multipartFile.transferTo(file); 
		    logger.info("Success to upload file = {}", file.getName());
		}else{
			throw new Exception("The request is not multipartHttpServletRequest");
		}

		
		boolean flag = file.renameTo(new File(filePath));

		/**
		 * 创建一个success 后缀的 flag 文件，仅仅标识文件已经上传完成。文件入库后，删除.success文件
		 */
		File successFile = new File(filePath + ".success");
		successFile.createNewFile();
		if (!flag)
			throw new RuntimeException("Failed to rename file");
		else
			logger.info("Success to rename file = {}", file.getName());

		HttpResponseHandler.handle(response, SuccessResponse.SUCCESS_RESPONSE);
	}

}
