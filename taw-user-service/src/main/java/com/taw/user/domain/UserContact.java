package com.taw.user.domain;
import java.io.Serializable;
import java.util.Date;



/**
 * t_um_user_contact
 * 
 * 
 * @author Code-Gen
 */
public class UserContact implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**/
	private Integer id;
	/**/
	private Integer userId;
	/**/
	private Integer coUserId;
	/**/
	private String remark;
	/**/
	private String type;
	/**/
	private Date crdt;
	/**/
	private Date updt;
	
	public Integer getId(){
		return id;
	}	
	public void setId (Integer id) {
		this.id = id;
	}
	
	public Integer getUserId(){
		return userId;
	}	
	public void setUserId (Integer userId) {
		this.userId = userId;
	}
	
	public Integer getCoUserId(){
		return coUserId;
	}	
	public void setCoUserId (Integer coUserId) {
		this.coUserId = coUserId;
	}
	
	public String getRemark(){
		return remark;
	}	
	public void setRemark (String remark) {
		this.remark = remark;
	}
	
	public String getType(){
		return type;
	}	
	public void setType (String type) {
		this.type = type;
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
	


}
