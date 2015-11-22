package com.taw.user.domain;
import java.io.Serializable;
import java.util.Date;



/**
 * t_um_user
 * 
 * 
 * @author Code-Gen
 */
public class UserDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/*用户主键*/
	private Long id;
	
	/*手机号/自动生成号*/
	private String mobile;
	
	/*0-未验证 ; 1-已验证*/
	private Short chcked;
	
	/*0-手机号; 1-自动生成号*/
	private Short mobileKind;
	
	/*有效/无效*/
	private String status;
	
	/*普通用户/管理员*/
	private String kind;
	
	/*用户性别*/
	private String sex;
	
	/*用户昵称*/
	private String nickname;
	
	/*登录密码*/
	private String password;
	
	/*微信/注册/QQ/微博*/
	private String channel;
	
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
	
	/*注册IP*/
	private String ip;
	
	/*创建日期*/
	private Date crdt;
	
	/*修改日期*/
	private Date updt;
	
	
	/**
	 * 
	 * @return 用户主键
	 */
	public Long getId(){
		return id;
	}
	
	/**
	 * 
	 * @param id 用户主键
	 */	
	public void setId (Long id) {
		this.id = id;
	}
	
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
	 * @return 0-未验证 ; 1-已验证
	 */
	public Short getChcked(){
		return chcked;
	}
	
	/**
	 * 
	 * @param chcked 0-未验证 ; 1-已验证
	 */	
	public void setChcked (Short chcked) {
		this.chcked = chcked;
	}
	
	/**
	 * 
	 * @return 0-手机号; 1-自动生成号
	 */
	public Short getMobileKind(){
		return mobileKind;
	}
	
	/**
	 * 
	 * @param mobileKind 0-手机号; 1-自动生成号
	 */	
	public void setMobileKind (Short mobileKind) {
		this.mobileKind = mobileKind;
	}
	
	/**
	 * 
	 * @return 有效/无效
	 */
	public String getStatus(){
		return status;
	}
	
	/**
	 * 
	 * @param status 有效/无效
	 */	
	public void setStatus (String status) {
		this.status = status;
	}
	
	/**
	 * 
	 * @return 普通用户/管理员
	 */
	public String getKind(){
		return kind;
	}
	
	/**
	 * 
	 * @param kind 普通用户/管理员
	 */	
	public void setKind (String kind) {
		this.kind = kind;
	}
	
	/**
	 * 
	 * @return 用户性别
	 */
	public String getSex(){
		return sex;
	}
	
	/**
	 * 
	 * @param sex 用户性别
	 */	
	public void setSex (String sex) {
		this.sex = sex;
	}
	
	/**
	 * 
	 * @return 用户昵称
	 */
	public String getNickname(){
		return nickname;
	}
	
	/**
	 * 
	 * @param nickname 用户昵称
	 */	
	public void setNickname (String nickname) {
		this.nickname = nickname;
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
	
	/**
	 * 
	 * @return 创建日期
	 */
	public Date getCrdt(){
		return crdt;
	}
	
	/**
	 * 
	 * @param crdt 创建日期
	 */	
	public void setCrdt (Date crdt) {
		this.crdt = crdt;
	}
	
	/**
	 * 
	 * @return 修改日期
	 */
	public Date getUpdt(){
		return updt;
	}
	
	/**
	 * 
	 * @param updt 修改日期
	 */	
	public void setUpdt (Date updt) {
		this.updt = updt;
	}
	


}
