package com.taw.scene.domain;
import java.io.Serializable;
import java.util.Date;



/**
 * t_tm_scene_tag
 * 
 * 
 * @author Code-Gen
 */
public class SceneTagDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/*主键*/
	private Long id;
	
	/*场景主键*/
	private Long sceneId;
	
	/*标签主键*/
	private Long tagId;
	
	/*标签内容*/
	private String tagName;
	
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
	 * @return 标签主键
	 */
	public Long getTagId(){
		return tagId;
	}
	
	/**
	 * 
	 * @param tagId 标签主键
	 */	
	public void setTagId (Long tagId) {
		this.tagId = tagId;
	}
	
	/**
	 * 
	 * @return 标签内容
	 */
	public String getTagName(){
		return tagName;
	}
	
	/**
	 * 
	 * @param tagName 标签内容
	 */	
	public void setTagName (String tagName) {
		this.tagName = tagName;
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
