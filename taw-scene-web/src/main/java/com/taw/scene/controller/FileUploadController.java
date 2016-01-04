package com.taw.scene.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Locale;
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

import com.taw.scene.configure.SceneServiceConfigure;

@Controller
public class FileUploadController {
	
	@Autowired
	@Qualifier("sceneServiceConfigure")
	private SceneServiceConfigure sceneServiceConfigure;
	
	

	@RequestMapping(value = "/file/upload.do", method = RequestMethod.POST)
	public void uploadFile(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String uuid = request.getParameter("uuid");//yyyyMMddHHmmss + 32位uuid
		String sourcefilename = request.getParameter("filename");
		String size = request.getParameter("size");
		System.out.println("param size = " + size);
		InputStream in = request.getInputStream();
		System.out.println("check size = " + request.getContentLength());
		String suffix = computeSuffix(sourcefilename);
		
		String dir = computeDir(uuid);
		mkdir(dir);
		String filePath = dir + File.pathSeparator + uuid + "." + suffix;
		File file = new File(filePath);
		FileOutputStream fos = new FileOutputStream(file, true);
		IOUtils.copyLarge(in, fos);
	}

	@RequestMapping(value = "/file/length.do", method = RequestMethod.POST)
	public void uploadLength(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String uuid = request.getParameter("uuid");
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
	
	private String computeSuffix(String filename){
		if (StringUtils.isEmpty(filename))
			throw new RuntimeException("filename is null");
		filename = filename.trim().toLowerCase();
		String[] strArray = filename.split("\\.");
		String suffix = strArray[strArray.length];
		if (sceneServiceConfigure.getSupportSuffix().contains(suffix))
			return suffix;
		else
			throw new RuntimeException("Only support "+sceneServiceConfigure.getSupportSuffixString());
	}
	
	private  String computeDir(String uuid){
		if (StringUtils.isEmpty(uuid))
			throw new RuntimeException("uuid is null");
		uuid = uuid.trim();		
		if (uuid.length()!=46)
			throw new RuntimeException("uuid is invalid");
		
		int length = uuid.length();
		String[] strArray = new String[]{uuid.substring(length-8,length-6),
				uuid.substring(length-6,length-4),
				uuid.substring(length-4,length-2),
				uuid.substring(length-2,length)};
		
		String path = sceneServiceConfigure.getUploadDir();
		for (String str : strArray){
			path = path + File.separator + computeDirPart(str);
		}
		return  path;
		
	}
	
	private static  String computeDirPart(String str){
		str = new Integer(Integer.parseInt(str, 16) % 16).toString();
		if (str.length() == 1)
			str = "0"+str;
		return str;
	}
	
	

	
}
