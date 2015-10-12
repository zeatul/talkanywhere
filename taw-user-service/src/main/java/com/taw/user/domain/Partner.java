package com.taw.user.domain;
import java.io.Serializable;
import java.util.Date;



/**
 * t_um_partner
 * 
 * 
 * @author Code-Gen
 */
public class Partner implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**/
	private Integer id;
	/*΢*/
	private String channel;
	/**/
	private String channelCode;
	/**/
	private Integer userId;
	/**/
	private Date crdt;
	
	public Integer getId(){
		return id;
	}	
	public void setId (Integer id) {
		this.id = id;
	}
	
	public String getChannel(){
		return channel;
	}	
	public void setChannel (String channel) {
		this.channel = channel;
	}
	
	public String getChannelCode(){
		return channelCode;
	}	
	public void setChannelCode (String channelCode) {
		this.channelCode = channelCode;
	}
	
	public Integer getUserId(){
		return userId;
	}	
	public void setUserId (Integer userId) {
		this.userId = userId;
	}
	
	public Date getCrdt(){
		return crdt;
	}	
	public void setCrdt (Date crdt) {
		this.crdt = crdt;
	}
	


}
