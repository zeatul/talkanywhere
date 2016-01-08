package com.taw.scene.domain;
import java.io.Serializable;
import java.util.Date;



/**
 * t_tm_scene_pic
 * 
 * 
 * @author Code-Gen
 */
public class ScenePicDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/*主键*/
	private Long id;
	
	/*场景ID*/
	private Long sceneId;
	
	/*消息ID/会话ID*/
	private Long mid;
	
	/*C：会话，M：私信*/
	private String kind;
	
	/*场景名称*/
	private String sceneName;
	
	/*图片ID*/
	private Long picId;
	
	/*图片UUID*/
	private String picUuid;
	
	/*创建时间*/
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
	 * @return 场景ID
	 */
	public Long getSceneId(){
		return sceneId;
	}
	
	/**
	 * 
	 * @param sceneId 场景ID
	 */	
	public void setSceneId (Long sceneId) {
		this.sceneId = sceneId;
	}
	
	/**
	 * 
	 * @return 消息ID/会话ID
	 */
	public Long getMid(){
		return mid;
	}
	
	/**
	 * 
	 * @param mid 消息ID/会话ID
	 */	
	public void setMid (Long mid) {
		this.mid = mid;
	}
	
	/**
	 * 
	 * @return C：会话，M：私信
	 */
	public String getKind(){
		return kind;
	}
	
	/**
	 * 
	 * @param kind C：会话，M：私信
	 */	
	public void setKind (String kind) {
		this.kind = kind;
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
	 * @return 图片UUID
	 */
	public String getPicUuid(){
		return picUuid;
	}
	
	/**
	 * 
	 * @param picUuid 图片UUID
	 */	
	public void setPicUuid (String picUuid) {
		this.picUuid = picUuid;
	}
	
	/**
	 * 
	 * @return 创建时间
	 */
	public Date getCrdt(){
		return crdt;
	}
	
	/**
	 * 
	 * @param crdt 创建时间
	 */	
	public void setCrdt (Date crdt) {
		this.crdt = crdt;
	}
	


}
