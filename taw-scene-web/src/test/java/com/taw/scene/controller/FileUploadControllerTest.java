package com.taw.scene.controller;

import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import com.hawk.utility.DateTools;

public class FileUploadControllerTest extends AbstractControllerTest{

	public FileUploadControllerTest() throws Exception {
		super();		
	}
	
	@Test
	public void testFileUpload() throws Exception{
		String path = contextPath + "/file/upload.do";
		String uuid = DateTools.convert(DateTools.now(), "yyyyMMddHHmmss") +UUID.randomUUID().toString().replace("-","")+ ".jpg";
		Map<String,String> params = genAuthMap();
		params.put("srcFile", "hello.jpg");
		params.put("uuid", uuid);
		byte[] b = "hello,您好,我是jpg测试".getBytes("utf8");
		Integer size = b.length;
		params.put("srcFileSize", size.toString());
		params.put("byteArraySize", size.toString());
		params.put("offset", "0");
		
		String result = httpClientHelper.post(path, b, params);
		
		printResult(result);
	}

}
