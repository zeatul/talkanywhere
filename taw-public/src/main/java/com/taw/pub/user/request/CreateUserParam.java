package com.taw.pub.user.request;

import com.hawk.utility.check.AllowNull;
import com.hawk.utility.check.MaxLength;
import com.hawk.utility.check.Regex;

public class CreateUserParam {
	
	/*手机号/自动生成号*/
	@AllowNull(allow=false)
	@MaxLength(max = 20)
	@Regex(pattern="[0-9]{1,20}")
	private String mobile;
	
	/*登录密码*/
	@AllowNull(allow=false)
	@MaxLength(max = 20)
	private String password;
	
	/*微信/注册/QQ/微博*/
	private String channel;
	
	/*设备唯一的串号*/
	@MaxLength(max = 50)
	private String imei;
	
	/*设备类型:android/ios/winphone/pc/mpc*/
	private String deviceKind;
	
	/*ios或安卓的版本号*/
	@MaxLength(max = 20)
	private String osVersion;
	
	/*三星/华为/苹果*/
	@MaxLength(max = 50)
	private String brand;
	
	/*厂商给设备定义的编号*/
	@MaxLength(max = 50)
	private String deviceModel;
	
	/*注册IP*/
	@MaxLength(max = 50)
	private String ip;

	
	/**
	 * 
	 * @return 手机号/自动生成号
	 */	
	public String getMobile(){
		return mobile;
	}
	
	/**
	 * 
	 * @param mobile 手机号/自动生成号
	 */	
	public void setMobile (String mobile) {
		this.mobile = mobile;
	}


	
	/**
	 * 
	 * @return 登录密码
	 */
	public String getPassword(){
		return password;
	}
	
	/**
	 * 
	 * @param password 登录密码
	 */	
	public void setPassword (String password) {
		this.password = password;
	}
	
	/**
	 * 
	 * @return 微信/注册/QQ/微博
	 */
	public String getChannel(){
		return channel;
	}
	
	/**
	 * 
	 * @param channel 微信/注册/QQ/微博
	 */	
	public void setChannel (String channel) {
		this.channel = channel;
	}
	
	/**
	 * 
	 * @return 设备唯一的串号
	 */
	public String getImei(){
		return imei;
	}
	
	/**
	 * 
	 * @param imei 设备唯一的串号
	 */	
	public void setImei (String imei) {
		this.imei = imei;
	}
	
	/**
	 * 
	 * @return 设备类型:android/ios/winphone/pc/mpc
	 */
	public String getDeviceKind(){
		return deviceKind;
	}
	
	/**
	 * 
	 * @param deviceKind 设备类型:android/ios/winphone/pc/mpc
	 */	
	public void setDeviceKind (String deviceKind) {
		this.deviceKind = deviceKind;
	}
	
	/**
	 * 
	 * @return ios或安卓的版本号
	 */
	public String getOsVersion(){
		return osVersion;
	}
	
	/**
	 * 
	 * @param osVersion ios或安卓的版本号
	 */	
	public void setOsVersion (String osVersion) {
		this.osVersion = osVersion;
	}
	
	/**
	 * 
	 * @return 三星/华为/苹果
	 */
	public String getBrand(){
		return brand;
	}
	
	/**
	 * 
	 * @param brand 三星/华为/苹果
	 */	
	public void setBrand (String brand) {
		this.brand = brand;
	}
	
	/**
	 * 
	 * @return 厂商给设备定义的编号
	 */
	public String getDeviceModel(){
		return deviceModel;
	}
	
	/**
	 * 
	 * @param deviceModel 厂商给设备定义的编号
	 */	
	public void setDeviceModel (String deviceModel) {
		this.deviceModel = deviceModel;
	}
	
	/**
	 * 
	 * @return 注册IP
	 */
	public String getIp(){
		return ip;
	}
	
	/**
	 * 
	 * @param ip 注册IP
	 */	
	public void setIp (String ip) {
		this.ip = ip;
	}
	
}
