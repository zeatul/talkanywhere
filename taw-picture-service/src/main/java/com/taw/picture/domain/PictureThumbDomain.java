package com.taw.picture.domain;
import java.io.Serializable;
import java.util.Date;



/**
 * t_pm_picture_thumb
 * 
 * 
 * @author Code-Gen
 */
public class PictureThumbDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/*主键*/
	private Long id;
	
	/*图片ID*/
	private Long picId;
	
	/*点赞者ID*/
	private Long userId;
	
	/*点赞者昵称*/
	private String nickname;
	
	/*up down*/
	private String kind;
	
	/*点赞时间*/
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
	 * @return 点赞者ID
	 */
	public Long getUserId(){
		return userId;
	}
	
	/**
	 * 
	 * @param userId 点赞者ID
	 */	
	public void setUserId (Long userId) {
		this.userId = userId;
	}
	
	/**
	 * 
	 * @return 点赞者昵称
	 */
	public String getNickname(){
		return nickname;
	}
	
	/**
	 * 
	 * @param nickname 点赞者昵称
	 */	
	public void setNickname (String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * 
	 * @return up down
	 */
	public String getKind(){
		return kind;
	}
	
	/**
	 * 
	 * @param kind up down
	 */	
	public void setKind (String kind) {
		this.kind = kind;
	}
	
	/**
	 * 
	 * @return 点赞时间
	 */
	public Date getCrdt(){
		return crdt;
	}
	
	/**
	 * 
	 * @param crdt 点赞时间
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
