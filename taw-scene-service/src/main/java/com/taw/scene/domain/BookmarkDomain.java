package com.taw.scene.domain;
import java.io.Serializable;
import java.util.Date;



/**
 * t_tm_bookmark
 * 
 * 
 * @author Code-Gen
 */
public class BookmarkDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/*主键*/
	private Long id;
	
	/*用户主键*/
	private Long userId;
	
	/*场景主键*/
	private Long sceneId;
	
	/*场景名称*/
	private String sceneName;
	
	/*收藏时间*/
	private Date bookTime;
	
	
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
	 * @return 收藏时间
	 */
	public Date getBookTime(){
		return bookTime;
	}
	
	/**
	 * 
	 * @param bookTime 收藏时间
	 */	
	public void setBookTime (Date bookTime) {
		this.bookTime = bookTime;
	}
	


}
