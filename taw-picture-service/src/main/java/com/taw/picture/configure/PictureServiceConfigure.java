package com.taw.picture.configure;

import java.util.HashSet;
import java.util.Set;

public class PictureServiceConfigure {
	
	

	public long getAllowFileSize() {
		return allowFileSize;
	}

	public void setAllowFileSize(long allowFileSize) {
		this.allowFileSize = allowFileSize;
	}

	public String getSupportSuffixString() {
		return supportSuffixString;
	}

	public Set<String> getSupportSuffix() {
		return supportSuffix;
	}

	public void setSupportSuffix(String suffixes) {
		String[] strArray = suffixes.split(",");
		supportSuffix = new HashSet<String>();
		for (String str : strArray){
			supportSuffix.add(str);
		}
		this.supportSuffixString = suffixes;
	}

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}

	private String uploadDir;
	
	
	private Set<String> supportSuffix;
	
	private String supportSuffixString;
	
	private long allowFileSize = 1024 * 1024 * 20 ;

}
