package com.taw.user.domain;
import java.io.Serializable;
import java.util.Date;



/**
 * t_um_partner
 * 
 * 
 * @author Code-Gen
 */
public class PartnerDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/*主键*/
	private Long id;
	
	/*微信/QQ/微博*/
	private String channel;
	
	/*第三方分配的用户编号*/
	private String channelCode;
	
	/*用户ID*/
	private Integer userId;
	
	/*创建日期*/
	private Date crdt;
	
	
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
	 * @return 微信/QQ/微博
	 */
	public String getChannel(){
		return channel;
	}
	
	/**
	 * 
	 * @param channel 微信/QQ/微博
	 */	
	public void setChannel (String channel) {
		this.channel = channel;
	}
	
	/**
	 * 
	 * @return 第三方分配的用户编号
	 */
	public String getChannelCode(){
		return channelCode;
	}
	
	/**
	 * 
	 * @param channelCode 第三方分配的用户编号
	 */	
	public void setChannelCode (String channelCode) {
		this.channelCode = channelCode;
	}
	
	/**
	 * 
	 * @return 用户ID
	 */
	public Integer getUserId(){
		return userId;
	}
	
	/**
	 * 
	 * @param userId 用户ID
	 */	
	public void setUserId (Integer userId) {
		this.userId = userId;
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
	


}
