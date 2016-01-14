package com.taw.scene.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import com.hawk.utility.DateTools;

public class FileUploadControllerTest extends AbstractControllerTest {

	public FileUploadControllerTest() throws Exception {
		super();
	}

	/**
	 * 直接上传文件全部内容
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFileUpload() {

		String localFilePath = "C:\\taw\\img\\img13.jpg";
		FileInputStream fileInputStream = null;
		try {
			File file = new File(localFilePath);
			fileInputStream = new FileInputStream(file);
			byte[] b = new byte[fileInputStream.available()];
			fileInputStream.read(b, 0, b.length);

			String path = contextPath + "/file/upload.do";
			String uuid = DateTools.convert(DateTools.now(), "yyyyMMddHHmmss") + UUID.randomUUID().toString().replace("-", "") + ".jpg";
			Map<String, String> params = genAuthMap();
			params.put("srcFile", "hello.jpg");
			params.put("uuid", uuid);

			Integer size = b.length;
			params.put("srcFileSize", size.toString());
			params.put("byteArraySize", size.toString());
			params.put("offset", "0");

			String result = httpClientHelper.post(path, b, params);

			printResult(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (IOException e) {

			}
		}
	}

	/**
	 * 分片上传文件，最终在服务器合并成功
	 * 
	 * @throws Exception
	 */
	public void testFileUploadWithParts() throws Exception {
		String localFilePath = "C:\\taw\\img\\img16.jpg";
		FileInputStream fileInputStream = null;
		try {
			File file = new File(localFilePath);
			fileInputStream = new FileInputStream(file);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (IOException e) {

			}
		}
	}

}
