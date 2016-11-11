package com.taw.picture.domain;
import java.io.Serializable;
import java.util.Date;



/**
 * t_pm_picture_forward
 * 
 * 
 * @author Code-Gen
 */
public class PictureForwardDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/*主键*/
	private Long id;
	
	/*图片ID*/
	private Long picId;
	
	/*转发者ID*/
	private Long userId;
	
	/*转发者昵称*/
	private String nickname;
	
	/*转发时间*/
	private Date crdt;
	
	/*用户性别*/
	private String sex;
	
	
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
	 * @return 图片ID
	 */
	public Long getPicId(){
		return picId;
	}
	
	/**
	 * 
	 * @param picId 图片ID
	 */	
	public void setPicId (Long picId) {
		this.picId = picId;
	}
	
	/**
	 * 
	 * @return 转发者ID
	 */
	public Long getUserId(){
		return userId;
	}
	
	/**
	 * 
	 * @param userId 转发者ID
	 */	
	public void setUserId (Long userId) {
		this.userId = userId;
	}
	
	/**
	 * 
	 * @return 转发者昵称
	 */
	public String getNickname(){
		return nickname;
	}
	
	/**
	 * 
	 * @param nickname 转发者昵称
	 */	
	public void setNickname (String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * 
	 * @return 转发时间
	 */
	public Date getCrdt(){
		return crdt;
	}
	
	/**
	 * 
	 * @param crdt 转发时间
	 */	
	public void setCrdt (Date crdt) {
		this.crdt = crdt;
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
	


}
