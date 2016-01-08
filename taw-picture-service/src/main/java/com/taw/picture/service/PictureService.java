package com.taw.picture.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hawk.pub.pkgen.PkGenerator;
import com.hawk.utility.DateTools;
import com.hawk.utility.check.CheckTools;
import com.taw.picture.configure.PictureServiceConfigure;
import com.taw.picture.domain.PictureDomain;
import com.taw.picture.exception.UploadFileNotFoundException;
import com.taw.pub.picture.enums.EnumPictureHotLevel;
import com.taw.pub.picture.enums.EnumPictureStatus;
import com.taw.pub.picture.request.InsrtPictureParam;

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
	public Long insrtPicture(InsrtPictureParam insrtPictureParam) throws Exception{
		CheckTools.check(insrtPictureParam);
		String uuid = insrtPictureParam.getUuid();
		ComputeResult computeResult = computeDir(uuid);		
		String dir = computeResult.getDir();
		String filePath = dir + File.separator + uuid;
		/**
		 * 判断文件是否存在，并取文件长度
		 */
		File file = new File(filePath);
		if (!file.exists())
			throw new UploadFileNotFoundException(uuid);
		
		long length = file.length();
		
		/**
		 * 入库，返回主键
		 */
		PictureDomain pictureDomain = new PictureDomain();
		
		pictureDomain.setUuid(uuid);
		
		pictureDomain.setSceneId(insrtPictureParam.getSceneId());
		pictureDomain.setSceneName(insrtPictureParam.getSceneName());
		
		pictureDomain.setUserId(insrtPictureParam.getUserId());
		pictureDomain.setNickname(insrtPictureParam.getNickname());
		
		pictureDomain.setCommentCount(0);		
		pictureDomain.setDownCount(0);
		pictureDomain.setUpCount(0);
		pictureDomain.setReferenceCount(1);
		pictureDomain.setSceneCount(1);
		pictureDomain.setForwardCount(0);		
		
		pictureDomain.setLSize(length);
		
		pictureDomain.setStatus(EnumPictureStatus.NORMAL.toString());
		pictureDomain.setHot(EnumPictureHotLevel.NORMAL.getValue());
		
		pictureDomain.setLocation(insrtPictureParam.getLocation());
		pictureDomain.setPhotoTime(insrtPictureParam.getPhotoTime());
		pictureDomain.setCrdt(DateTools.now());
		pictureDomain.setId(PkGenerator.genPk());
		
		return pictureDomain.getId();
		
		
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
