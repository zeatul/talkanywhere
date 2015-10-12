package com.taw.user.domain;
import java.io.Serializable;
import java.util.Date;



/**
 * t_um_login
 * 
 * 
 * @author Code-Gen
 */
public class Login implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**/
	private String token;
	/**/
	private Integer userId;
	/**/
	private Date loginDate;
	/**/
	private Date lastAccessDate;
	/*tokenʧЧ*/
	private Date expireDate;
	/**/
	private Short kind;
	/**/
	private String imei;
	/*android/ios/winphone/pc/mpc*/
	private String deviceKind;
	/*ios*/
	private String osVersion;
	/**/
	private String brand;
	/**/
	private String deviceModel;
	/**/
	private String ip;
	
	public String getToken(){
		return token;
	}	
	public void setToken (String token) {
		this.token = token;
	}
	
	public Integer getUserId(){
		return userId;
	}	
	public void setUserId (Integer userId) {
		this.userId = userId;
	}
	
	public Date getLoginDate(){
		return loginDate;
	}	
	public void setLoginDate (Date loginDate) {
		this.loginDate = loginDate;
	}
	
	public Date getLastAccessDate(){
		return lastAccessDate;
	}	
	public void setLastAccessDate (Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}
	
	public Date getExpireDate(){
		return expireDate;
	}	
	public void setExpireDate (Date expireDate) {
		this.expireDate = expireDate;
	}
	
	public Short getKind(){
		return kind;
	}	
	public void setKind (Short kind) {
		this.kind = kind;
	}
	
	public String getImei(){
		return imei;
	}	
	public void setImei (String imei) {
		this.imei = imei;
	}
	
	public String getDeviceKind(){
		return deviceKind;
	}	
	public void setDeviceKind (String deviceKind) {
		this.deviceKind = deviceKind;
	}
	
	public String getOsVersion(){
		return osVersion;
	}	
	public void setOsVersion (String osVersion) {
		this.osVersion = osVersion;
	}
	
	public String getBrand(){
		return brand;
	}	
	public void setBrand (String brand) {
		this.brand = brand;
	}
	
	public String getDeviceModel(){
		return deviceModel;
	}	
	public void setDeviceModel (String deviceModel) {
		this.deviceModel = deviceModel;
	}
	
	public String getIp(){
		return ip;
	}	
	public void setIp (String ip) {
		this.ip = ip;
	}
	


}
