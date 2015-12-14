package com.taw.user.domain;
import java.io.Serializable;
import java.util.Date;



/**
 * t_um_login
 * 
 * 
 * @author Code-Gen
 */
public class LoginDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/*登录唯一标识*/
	private String token;
	
	/*用户ID*/
	private Long userId;
	
	/*设备唯一的串号*/
	private String imei;
	
	/*设备类型:android/ios/winphone/pc/mpc*/
	private String deviceKind;
	
	/*ios或安卓的版本号*/
	private String osVersion;
	
	/*三星/华为/苹果*/
	private String brand;
	
	/*厂商给设备定义的编号*/
	private String deviceModel;
	
	/*登录IP*/
	private String ip;
	
	/*长期/短期*/
	private String kind;
	
	/*创建日期*/
	private Date loginDate;
	
	/*最近访问日期*/
	private Date lastAccessDate;
	
	/*token失效日期*/
	private Date expireDate;
	
	/*注销日期*/
	private Date logoutDate;
	
	
	/**
	 * 
	 * @return 登录唯一标识
	 */
	public String getToken(){
		return token;
	}
	
	/**
	 * 
	 * @param token 登录唯一标识
	 */	
	public void setToken (String token) {
		this.token = token;
	}
	
	/**
	 * 
	 * @return 用户ID
	 */
	public Long getUserId(){
		return userId;
	}
	
	/**
	 * 
	 * @param userId 用户ID
	 */	
	public void setUserId (Long userId) {
		this.userId = userId;
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
	 * @return 登录IP
	 */
	public String getIp(){
		return ip;
	}
	
	/**
	 * 
	 * @param ip 登录IP
	 */	
	public void setIp (String ip) {
		this.ip = ip;
	}
	
	/**
	 * 
	 * @return 长期/短期
	 */
	public String getKind(){
		return kind;
	}
	
	/**
	 * 
	 * @param kind 长期/短期
	 */	
	public void setKind (String kind) {
		this.kind = kind;
	}
	
	/**
	 * 
	 * @return 创建日期
	 */
	public Date getLoginDate(){
		return loginDate;
	}
	
	/**
	 * 
	 * @param loginDate 创建日期
	 */	
	public void setLoginDate (Date loginDate) {
		this.loginDate = loginDate;
	}
	
	/**
	 * 
	 * @return 最近访问日期
	 */
	public Date getLastAccessDate(){
		return lastAccessDate;
	}
	
	/**
	 * 
	 * @param lastAccessDate 最近访问日期
	 */	
	public void setLastAccessDate (Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}
	
	/**
	 * 
	 * @return token失效日期
	 */
	public Date getExpireDate(){
		return expireDate;
	}
	
	/**
	 * 
	 * @param expireDate token失效日期
	 */	
	public void setExpireDate (Date expireDate) {
		this.expireDate = expireDate;
	}
	
	/**
	 * 
	 * @return 注销日期
	 */
	public Date getLogoutDate(){
		return logoutDate;
	}
	
	/**
	 * 
	 * @param logoutDate 注销日期
	 */	
	public void setLogoutDate (Date logoutDate) {
		this.logoutDate = logoutDate;
	}
	


}
