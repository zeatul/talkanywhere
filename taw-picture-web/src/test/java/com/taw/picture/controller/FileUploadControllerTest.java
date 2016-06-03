package com.taw.picture.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import com.hawk.utility.DateTools;
import com.hawk.utility.JsonTools;
import com.taw.pub.scene.request.UploadLengthParam;

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

		String localFilePath = "C:\\mydata\\share\\jpg\\QQ20160603102259.png";
		FileInputStream fileInputStream = null;
		try {
			File file = new File(localFilePath);
			fileInputStream = new FileInputStream(file);
			byte[] b = new byte[fileInputStream.available()];
			fileInputStream.read(b, 0, b.length);

			String path = contextPath + "/pic/upload.do";
			String uuid = DateTools.convert(DateTools.now(), "yyyyMMddHHmmss") + UUID.randomUUID().toString().replace("-", "") + ".jpg";
			Map<String, String> params = genAuthMap();
			params.put("srcFile", "hello.jpg");
			params.put("uuid", uuid);

			Integer size = b.length;
			params.put("srcFileSize", size.toString());
			params.put("byteArraySize", size.toString());
			params.put("offset", "0");

			String result = httpClientHelper.post(path, b, params,0,b.length);

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
		String fileUploadPath = contextPath + "/pic/upload.do";
		String fileLengthPath = contextPath + "/pic/length.do";
		String uuid = DateTools.convert(DateTools.now(), "yyyyMMddHHmmss") + UUID.randomUUID().toString().replace("-", "") + ".jpg";
		
		try {
			File file = new File(localFilePath);
			fileInputStream = new FileInputStream(file);
			Integer fileSize = fileInputStream.available();
			Integer offset = 0;		
			while (true){
				
				/**
				 * 上传 
				 */
				byte[] b = new byte[50000];
				int readLength = fileInputStream.read(b, 0, b.length);
				if (readLength == -1){
					break;
				}
				Map<String, String> params = genAuthMap();
				params.put("srcFile", "hello.jpg");
				params.put("uuid", uuid);

				Integer size = b.length;
				params.put("srcFileSize", fileSize.toString());
				params.put("byteArraySize", size.toString());
				params.put("offset", "0");

				String result = httpClientHelper.post(fileUploadPath, b, params,0,b.length);
				
				offset = offset + readLength;

				printResult("fileUpload.result = " + result + ", offset =" + offset);
				
				/**
				 * 取已经上传的文件长度
				 */
				UploadLengthParam uploadLengthParam = new UploadLengthParam();
				uploadLengthParam.setUuid(uuid);
				String content = JsonTools.toJsonString(uploadLengthParam);
				printSend("fileLength.content = " + content);
				result = httpClientHelper.post(fileLengthPath, content, genAuthMap());
				
				printResult("fileLength.result = " +result);
				

				
				
			}
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
