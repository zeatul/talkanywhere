package com.taw.user.domain;
import java.io.Serializable;
import java.util.Date;



/**
 * t_um_version
 * 
 * 
 * @author Code-Gen
 */
public class VersionDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/*主键*/
	private Long id;
	
	/*模块号*/
	private String model;
	
	/*版本号*/
	private String version;
	
	/*版本描述*/
	private String description;
	
	/*强迫更新*/
	private Short forced;
	
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
	 * @return 模块号
	 */
	public String getModel(){
		return model;
	}
	
	/**
	 * 
	 * @param model 模块号
	 */	
	public void setModel (String model) {
		this.model = model;
	}
	
	/**
	 * 
	 * @return 版本号
	 */
	public String getVersion(){
		return version;
	}
	
	/**
	 * 
	 * @param version 版本号
	 */	
	public void setVersion (String version) {
		this.version = version;
	}
	
	/**
	 * 
	 * @return 版本描述
	 */
	public String getDescription(){
		return description;
	}
	
	/**
	 * 
	 * @param description 版本描述
	 */	
	public void setDescription (String description) {
		this.description = description;
	}
	
	/**
	 * 
	 * @return 强迫更新
	 */
	public Short getForced(){
		return forced;
	}
	
	/**
	 * 
	 * @param forced 强迫更新
	 */	
	public void setForced (Short forced) {
		this.forced = forced;
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
