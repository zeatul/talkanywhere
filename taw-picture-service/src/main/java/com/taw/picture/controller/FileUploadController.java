package com.taw.picture.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Locale;

import javax.management.RuntimeErrorException;
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

import com.hawk.pub.web.HttpRequestHandler;
import com.hawk.pub.web.HttpResponseHandler;
import com.hawk.pub.web.SuccessResponse;
import com.hawk.utility.check.CheckTools;
import com.taw.picture.configure.PictureServiceConfigure;
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
	
	

	@RequestMapping(value = "/file/upload.do", method = RequestMethod.POST)
	public void uploadFile(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/**
		 * yyyyMMddHHmmss + 32位uuid
		 */
		String uuid = request.getParameter("uuid");
		ComputeResult computeResult = pictureService.computeDir(uuid);
		String dir = computeResult.getDir();
		String suffix = computeResult.getSuffix();	
		/**
		 * 上传的本地源文件名称
		 */
		String srcFile = request.getParameter("srcFile");
		
		/**
		 * 上传的本地源文件实际大小
		 */
		String srcFileSizeStr = request.getParameter("srcFileSize");	
		if(StringUtils.isEmpty(srcFileSizeStr)){
			throw new RuntimeException("srcFileSize is empty");
		}
		long srcFileSize = new Long(srcFileSizeStr);
		/**
		 * 本次上传的byte数组大小
		 */
		String byteArraySizeStr = request.getParameter("byteArraySize");	
		if(StringUtils.isEmpty(byteArraySizeStr)){
			throw new RuntimeException("byteArraySize is empty");
		}
		long byteArraySize = new Long(byteArraySizeStr);
		
		/**
		 * 本次上传内容 在文件的起始位置
		 */
		String offsetStr = request.getParameter("offset");
		if(StringUtils.isEmpty(byteArraySizeStr)){
			throw new RuntimeException("offset is empty");
		}
		long offset = new Long(offsetStr);
		if (offset > srcFileSize)
			throw new RuntimeException("offset is wrong");
		
		
		InputStream in = request.getInputStream();
		mkdir(dir);
		String filePath = dir + File.separator + uuid ;
		File file = new File(filePath+".tmp");
		
		long maxSize = pictureServiceConfigure.getAllowFileSize();
		if (srcFileSize > maxSize || file.length() >maxSize  )
			throw new RuntimeException("The upload file size shouln't be over " + maxSize);
		
		FileOutputStream fos = null;
		try{
			if (file.exists() && file.length() >= srcFileSize){
				logger.info("Success to upload file = {}",file.getName());
			}else{
				fos = new FileOutputStream(file, true);
				IOUtils.copyLarge(in, fos);
			}
		}finally{	
			try {
				if (fos != null)
					fos.close();
			} catch (Exception e) {
				logger.error("failed to close file");
			}
		}
		
		
		
		if (file.length() >= srcFileSize){
			logger.info("Success to upload file = {}",file.getName());
			boolean  flag = file.renameTo(new File(filePath)); 
			
			/**
			 * 创建一个success 后缀的 flag 文件，仅仅标识文件已经上传完成。
			 */
			File successFile = new File(filePath + ".success");
			successFile.createNewFile();
			if (!flag)
				throw new RuntimeException("Failed to rename file");
			else
				logger.info("Success to rename file = {}",file.getName());
		}
		
		HttpResponseHandler.handle(response, SuccessResponse.SUCCESS_RESPONSE);
	}

	@RequestMapping(value = "/file/length.do", method = RequestMethod.POST)
	public void uploadLength(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UploadLengthParam param = HttpRequestHandler.handle(request, UploadLengthParam.class);
		CheckTools.check(param);

		String uuid = param.getUuid();
		String filePath = pictureService.computeDir(uuid).getDir()+File.separator + uuid;
		File file = new File(filePath+".tmp");
		long size = 0;
		if (file.exists()){
			size = file.length();
		}else{
			file = new File(filePath);
			if (file.exists()){
				size = file.length();
			}
		}
		UploadLengthResponse uploadLengthResponse = new UploadLengthResponse();
		uploadLengthResponse.setSize(size);
		
		HttpResponseHandler.handle(response, SuccessResponse.build(uploadLengthResponse));
	}
	
	private void mkdir(String path){;
		File f = new File(path);
		if (!f.exists()){
			if (!f.mkdirs()){
				if (!f.exists())
					throw new RuntimeException("Failed to mkdir "+path);
			}else{
				f.setReadable(true);
				f.setWritable(true);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	

	
}
