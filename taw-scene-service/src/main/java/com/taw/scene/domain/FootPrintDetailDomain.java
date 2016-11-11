package com.taw.scene.domain;
import java.io.Serializable;
import java.util.Date;



/**
 * t_tm_foot_print_detail
 * 
 * 
 * @author Code-Gen
 */
public class FootPrintDetailDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/*主键*/
	private Long id;
	
	/*登录TOKEN*/
	private String token;
	
	/*用户主键*/
	private Long userId;
	
	/*场景主键*/
	private Long sceneId;
	
	/*场景名称*/
	private String sceneName;
	
	/*分配昵称*/
	private String nickname;
	
	/*最后进入时间*/
	private Date inTime;
	
	/*进入次数累计*/
	private Date outTime;
	
	/*总停留时间*/
	private Integer staySpan;
	
	/*离开类型*/
	private String leaveType;
	
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
	 * @return 登录TOKEN
	 */
	public String getToken(){
		return token;
	}
	
	/**
	 * 
	 * @param token 登录TOKEN
	 */	
	public void setToken (String token) {
		this.token = token;
	}
	
	/**
	 * 
	 * @return 用户主键
	 */
	public Long getUserId(){
		return userId;
	}
	
	/**
	 * 
	 * @param userId 用户主键
	 */	
	public void setUserId (Long userId) {
		this.userId = userId;
	}
	
	/**
	 * 
	 * @return 场景主键
	 */
	public Long getSceneId(){
		return sceneId;
	}
	
	/**
	 * 
	 * @param sceneId 场景主键
	 */	
	public void setSceneId (Long sceneId) {
		this.sceneId = sceneId;
	}
	
	/**
	 * 
	 * @return 场景名称
	 */
	public String getSceneName(){
		return sceneName;
	}
	
	/**
	 * 
	 * @param sceneName 场景名称
	 */	
	public void setSceneName (String sceneName) {
		this.sceneName = sceneName;
	}
	
	/**
	 * 
	 * @return 分配昵称
	 */
	public String getNickname(){
		return nickname;
	}
	
	/**
	 * 
	 * @param nickname 分配昵称
	 */	
	public void setNickname (String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * 
	 * @return 最后进入时间
	 */
	public Date getInTime(){
		return inTime;
	}
	
	/**
	 * 
	 * @param inTime 最后进入时间
	 */	
	public void setInTime (Date inTime) {
		this.inTime = inTime;
	}
	
	/**
	 * 
	 * @return 进入次数累计
	 */
	public Date getOutTime(){
		return outTime;
	}
	
	/**
	 * 
	 * @param outTime 进入次数累计
	 */	
	public void setOutTime (Date outTime) {
		this.outTime = outTime;
	}
	
	/**
	 * 
	 * @return 总停留时间
	 */
	public Integer getStaySpan(){
		return staySpan;
	}
	
	/**
	 * 
	 * @param staySpan 总停留时间
	 */	
	public void setStaySpan (Integer staySpan) {
		this.staySpan = staySpan;
	}
	
	/**
	 * 
	 * @return 离开类型
	 */
	public String getLeaveType(){
		return leaveType;
	}
	
	/**
	 * 
	 * @param leaveType 离开类型
	 */	
	public void setLeaveType (String leaveType) {
		this.leaveType = leaveType;
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
