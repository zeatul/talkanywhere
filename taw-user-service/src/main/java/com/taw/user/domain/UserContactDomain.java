package com.taw.user.domain;
import java.io.Serializable;
import java.util.Date;



/**
 * t_um_user_contact
 * 
 * 
 * @author Code-Gen
 */
public class UserContactDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/*主键*/
	private Long id;
	
	/*用户ID*/
	private Long userId;
	
	/*关系用户ID*/
	private Long coUserId;
	
	/*关系用户备注*/
	private String remark;
	
	/*关系类型*/
	private String type;
	
	/*创建日期*/
	private Date crdt;
	
	/*修改日期*/
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
	 * @return 关系用户ID
	 */
	public Long getCoUserId(){
		return coUserId;
	}
	
	/**
	 * 
	 * @param coUserId 关系用户ID
	 */	
	public void setCoUserId (Long coUserId) {
		this.coUserId = coUserId;
	}
	
	/**
	 * 
	 * @return 关系用户备注
	 */
	public String getRemark(){
		return remark;
	}
	
	/**
	 * 
	 * @param remark 关系用户备注
	 */	
	public void setRemark (String remark) {
		this.remark = remark;
	}
	
	/**
	 * 
	 * @return 关系类型
	 */
	public String getType(){
		return type;
	}
	
	/**
	 * 
	 * @param type 关系类型
	 */	
	public void setType (String type) {
		this.type = type;
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
