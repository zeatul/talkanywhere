package com.taw.picture.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.taw.picture.configure.PictureServiceConfigure;

@Service
public class PictureService {
	
	@Autowired
	@Qualifier("pictureServiceConfigure")
	private PictureServiceConfigure pictureServiceConfigure;
	
	
	/**
	 * 
	 * @param uuid 
	 * @return 主键
	 */
	public Long insrtPicture(String uuid){
		ComputeResult computeResult = computeDir(uuid);
		String dir = computeResult.getDir();
		String filePath = dir + File.separator + uuid;
		/**
		 * 判断文件是否存在，并取文件长度
		 */
		
		/**
		 * 入库，返回主键
		 */
	}
	
	public static class ComputeResult{
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
	
	
	/**
	 * 根据uuid ，计算 suffix 和  目录
	 * @param uuid
	 * @return
	 */
	public  ComputeResult computeDir(String uuid){
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
		
		String path = pictureServiceConfigure.getUploadDir();
		for (String str : strArray){
			path = path + File.separator + computeDirPart(str);
		}
		
		computeResult.setDir(path);
		return  computeResult;
		
	}
	
	/**
	 * 16进制，切分为16份
	 * @param str
	 * @return
	 */
	private static  String computeDirPart(String str){
		str = new Integer(Integer.parseInt(str, 16) % 16).toString();
		if (str.length() == 1)
			str = "0"+str;
		return str;
	}

}
