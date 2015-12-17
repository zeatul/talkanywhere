package com.hawk.pub.sms.domain;
import java.io.Serializable;
import java.util.Date;



/**
 * t_sm_sms
 * 
 * 
 * @author Code-Gen
 */
public class SmsDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/*主键*/
	private Long id;
	
	/*手机号*/
	private String mobile;
	
	/*消息模板ID*/
	private String tplId;
	
	/*消息动态参数*/
	private String params;
	
	/*消息体*/
	private String message;
	
	/*消息类型*/
	private String kind;
	
	/*发送状态*/
	private String status;
	
	/*发送时间*/
	private Date sendTime;
	
	/*失效时间*/
	private Date expireTime;
	
	/*发送次数*/
	private Integer sendTimes;
	
	/*最大可发送次数*/
	private Integer maxTimes;
	
	/*短信平台发送成功返回的消息ID*/
	private String sid;
	
	/*短信平台返回的错误代码*/
	private String errCode;
	
	/*短信平台返回的错误原因*/
	private String errMsg;
	
	/*创建时间*/
	private Date crdt;
	
	/*更新时间*/
	private Date updt;
	
	
	/**
	 * 
	 * @return 主键
	 */
	public Long getId(){
		return id;
	}
	
	/**
	 * 
	 * @param id 主键
	 */	
	public void setId (Long id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return 手机号
	 */
	public String getMobile(){
		return mobile;
	}
	
	/**
	 * 
	 * @param mobile 手机号
	 */	
	public void setMobile (String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * 
	 * @return 消息模板ID
	 */
	public String getTplId(){
		return tplId;
	}
	
	/**
	 * 
	 * @param tplId 消息模板ID
	 */	
	public void setTplId (String tplId) {
		this.tplId = tplId;
	}
	
	/**
	 * 
	 * @return 消息动态参数
	 */
	public String getParams(){
		return params;
	}
	
	/**
	 * 
	 * @param params 消息动态参数
	 */	
	public void setParams (String params) {
		this.params = params;
	}
	
	/**
	 * 
	 * @return 消息体
	 */
	public String getMessage(){
		return message;
	}
	
	/**
	 * 
	 * @param message 消息体
	 */	
	public void setMessage (String message) {
		this.message = message;
	}
	
	/**
	 * 
	 * @return 消息类型
	 */
	public String getKind(){
		return kind;
	}
	
	/**
	 * 
	 * @param kind 消息类型
	 */	
	public void setKind (String kind) {
		this.kind = kind;
	}
	
	/**
	 * 
	 * @return 发送状态
	 */
	public String getStatus(){
		return status;
	}
	
	/**
	 * 
	 * @param status 发送状态
	 */	
	public void setStatus (String status) {
		this.status = status;
	}
	
	/**
	 * 
	 * @return 发送时间
	 */
	public Date getSendTime(){
		return sendTime;
	}
	
	/**
	 * 
	 * @param sendTime 发送时间
	 */	
	public void setSendTime (Date sendTime) {
		this.sendTime = sendTime;
	}
	
	/**
	 * 
	 * @return 失效时间
	 */
	public Date getExpireTime(){
		return expireTime;
	}
	
	/**
	 * 
	 * @param expireTime 失效时间
	 */	
	public void setExpireTime (Date expireTime) {
		this.expireTime = expireTime;
	}
	
	/**
	 * 
	 * @return 发送次数
	 */
	public Integer getSendTimes(){
		return sendTimes;
	}
	
	/**
	 * 
	 * @param sendTimes 发送次数
	 */	
	public void setSendTimes (Integer sendTimes) {
		this.sendTimes = sendTimes;
	}
	
	/**
	 * 
	 * @return 最大可发送次数
	 */
	public Integer getMaxTimes(){
		return maxTimes;
	}
	
	/**
	 * 
	 * @param maxTimes 最大可发送次数
	 */	
	public void setMaxTimes (Integer maxTimes) {
		this.maxTimes = maxTimes;
	}
	
	/**
	 * 
	 * @return 短信平台发送成功返回的消息ID
	 */
	public String getSid(){
		return sid;
	}
	
	/**
	 * 
	 * @param sid 短信平台发送成功返回的消息ID
	 */	
	public void setSid (String sid) {
		this.sid = sid;
	}
	
	/**
	 * 
	 * @return 短信平台返回的错误代码
	 */
	public String getErrCode(){
		return errCode;
	}
	
	/**
	 * 
	 * @param errCode 短信平台返回的错误代码
	 */	
	public void setErrCode (String errCode) {
		this.errCode = errCode;
	}
	
	/**
	 * 
	 * @return 短信平台返回的错误原因
	 */
	public String getErrMsg(){
		return errMsg;
	}
	
	/**
	 * 
	 * @param errMsg 短信平台返回的错误原因
	 */	
	public void setErrMsg (String errMsg) {
		this.errMsg = errMsg;
	}
	
	/**
	 * 
	 * @return 创建时间
	 */
	public Date getCrdt(){
		return crdt;
	}
	
	/**
	 * 
	 * @param crdt 创建时间
	 */	
	public void setCrdt (Date crdt) {
		this.crdt = crdt;
	}
	
	/**
	 * 
	 * @return 更新时间
	 */
	public Date getUpdt(){
		return updt;
	}
	
	/**
	 * 
	 * @param updt 更新时间
	 */	
	public void setUpdt (Date updt) {
		this.updt = updt;
	}
	


}
