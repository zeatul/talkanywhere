package com.taw.user.domain;
import java.io.Serializable;
import java.util.Date;



/**
 * t_um_user
 * 
 * 
 * @author Code-Gen
 */
public class User implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**/
	private Integer id;
	/**/
	private String mobile;
	/*0-δ*/
	private Short chcked;
	/*0-*/
	private Short mobileKind;
	/**/
	private String status;
	/**/
	private String kind;
	/**/
	private String nickname;
	/**/
	private String password;
	/*΢*/
	private String channel;
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
	private Date crdt;
	/**/
	private Date updt;
	/*ע*/
	private String ip;
	
	public Integer getId(){
		return id;
	}	
	public void setId (Integer id) {
		this.id = id;
	}
	
	public String getMobile(){
		return mobile;
	}	
	public void setMobile (String mobile) {
		this.mobile = mobile;
	}
	
	public Short getChcked(){
		return chcked;
	}	
	public void setChcked (Short chcked) {
		this.chcked = chcked;
	}
	
	public Short getMobileKind(){
		return mobileKind;
	}	
	public void setMobileKind (Short mobileKind) {
		this.mobileKind = mobileKind;
	}
	
	public String getStatus(){
		return status;
	}	
	public void setStatus (String status) {
		this.status = status;
	}
	
	public String getKind(){
		return kind;
	}	
	public void setKind (String kind) {
		this.kind = kind;
	}
	
	public String getNickname(){
		return nickname;
	}	
	public void setNickname (String nickname) {
		this.nickname = nickname;
	}
	
	public String getPassword(){
		return password;
	}	
	public void setPassword (String password) {
		this.password = password;
	}
	
	public String getChannel(){
		return channel;
	}	
	public void setChannel (String channel) {
		this.channel = channel;
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
	
	public Date getCrdt(){
		return crdt;
	}	
	public void setCrdt (Date crdt) {
		this.crdt = crdt;
	}
	
	public Date getUpdt(){
		return updt;
	}	
	public void setUpdt (Date updt) {
		this.updt = updt;
	}
	
	public String getIp(){
		return ip;
	}	
	public void setIp (String ip) {
		this.ip = ip;
	}
	


}
