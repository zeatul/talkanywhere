package com.taw.scene.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Locale;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
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
import com.taw.pub.scene.request.UploadLengthParam;
import com.taw.pub.scene.response.UploadLengthResponse;
import com.taw.scene.configure.SceneServiceConfigure;

@Controller
public class FileUploadController {
	
	@Autowired
	@Qualifier("sceneServiceConfigure")
	private SceneServiceConfigure sceneServiceConfigure;
	
	

	@RequestMapping(value = "/file/upload.do", method = RequestMethod.POST)
	public void uploadFile(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/**
		 * yyyyMMddHHmmss + 32位uuid
		 */
		String uuid = request.getParameter("uuid");
		ComputeResult computeResult = computeDir(uuid);
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
		
		
		InputStream in = request.getInputStream();
		mkdir(dir);
		String filePath = dir + File.separator + uuid ;
		File file = new File(filePath+".tmp");
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream(file, true);
			IOUtils.copyLarge(in, fos);
		}finally{		
			fos.close();
		}
		
		if (file.length() >= srcFileSize){
			boolean  flag = file.renameTo(new File(filePath)); 
			if (!flag)
				throw new RuntimeException("Failed to rename file");
		}
		
		HttpResponseHandler.handle(response, SuccessResponse.SUCCESS_RESPONSE);
	}

	@RequestMapping(value = "/file/length.do", method = RequestMethod.POST)
	public void uploadLength(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UploadLengthParam param = HttpRequestHandler.handle(request, UploadLengthParam.class);
		CheckTools.check(param);

		String uuid = param.getUuid();
		String filePath = computeDir(uuid).getDir()+File.separator + uuid;
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
	
	
	
	private static class ComputeResult{
		public String getSuffix() {
			return suffix;
		}
		public void setSuffix(String suffix) {
			this.suffix = suffix;
		}
		public String getDir() {
			return dir;
		}
		public void setDir(String dir) {
			this.dir = dir;
		}
		private String suffix;
		private String dir;
	}
	
	private  ComputeResult computeDir(String uuid){
		if (StringUtils.isEmpty(uuid))
			throw new RuntimeException("uuid is null");
		uuid = uuid.trim();		
		if (uuid.length() <48)
			throw new RuntimeException("invalid uuid , uuid=yyyyMMddHHmmss+32uuid + .suffix");
		
		String[] strArray = uuid.split("\\.");
		
		if (strArray.length != 2)
			throw new RuntimeException("invalid uuid , uuid=yyyyMMddHHmmss+32uuid + .suffix");
		
		ComputeResult computeResult  = new ComputeResult();
		computeResult.setSuffix(strArray[1]);
		uuid = strArray[0];
		int length = uuid.length();
		strArray = new String[]{uuid.substring(length-8,length-6),
				uuid.substring(length-6,length-4),
				uuid.substring(length-4,length-2),
				uuid.substring(length-2,length)};
		
		String path = sceneServiceConfigure.getUploadDir();
		for (String str : strArray){
			path = path + File.separator + computeDirPart(str);
		}
		
		computeResult.setDir(path);
		return  computeResult;
		
	}
	
	private static  String computeDirPart(String str){
		str = new Integer(Integer.parseInt(str, 16) % 16).toString();
		if (str.length() == 1)
			str = "0"+str;
		return str;
	}
	
	

	
}
