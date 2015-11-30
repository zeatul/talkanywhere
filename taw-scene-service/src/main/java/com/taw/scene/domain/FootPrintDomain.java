package com.taw.scene.domain;
import java.io.Serializable;
import java.util.Date;



/**
 * t_tm_foot_print
 * 
 * 
 * @author Code-Gen
 */
public class FootPrintDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/*主键*/
	private Long id;
	
	/*用户主键*/
	private Long userId;
	
	/*场景主键*/
	private Long sceneId;
	
	/*场景名称*/
	private String sceneName;
	
	/*最后进入时间*/
	private Date lastEnterTime;
	
	/*最后离开时间*/
	private Date lastLeaveTime;
	
	/*进入次数累计*/
	private Integer enterTimes;
	
	/*总停留时间*/
	private Integer staySpan;
	
	
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
	 * @return 最后进入时间
	 */
	public Date getLastEnterTime(){
		return lastEnterTime;
	}
	
	/**
	 * 
	 * @param lastEnterTime 最后进入时间
	 */	
	public void setLastEnterTime (Date lastEnterTime) {
		this.lastEnterTime = lastEnterTime;
	}
	
	/**
	 * 
	 * @return 最后离开时间
	 */
	public Date getLastLeaveTime(){
		return lastLeaveTime;
	}
	
	/**
	 * 
	 * @param lastLeaveTime 最后离开时间
	 */	
	public void setLastLeaveTime (Date lastLeaveTime) {
		this.lastLeaveTime = lastLeaveTime;
	}
	
	/**
	 * 
	 * @return 进入次数累计
	 */
	public Integer getEnterTimes(){
		return enterTimes;
	}
	
	/**
	 * 
	 * @param enterTimes 进入次数累计
	 */	
	public void setEnterTimes (Integer enterTimes) {
		this.enterTimes = enterTimes;
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
	


}
