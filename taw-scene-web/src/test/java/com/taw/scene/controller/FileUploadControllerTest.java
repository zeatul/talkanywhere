package com.taw.scene.controller;

import java.util.Map;

import org.junit.Test;

public class FileUploadControllerTest extends AbstractControllerTest{

	public FileUploadControllerTest() throws Exception {
		super();		
	}
	
	@Test
	public void testFileUpload() throws Exception{
		String path = contextPath + "/file/upload.do";
		String uuid = "abcdedfha";
		Map<String,String> params = genAuthMap();
		params.put("filetype", "jpg");
		params.put("filename", "hello");
		params.put("uuid", uuid);
		byte[] b = "hello,您好".getBytes("utf8");
		
		httpClientHelper.post(path, b, params);
		
		
	}

}
