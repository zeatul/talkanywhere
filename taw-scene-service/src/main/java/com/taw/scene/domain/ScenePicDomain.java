package com.taw.scene.domain;
import java.io.Serializable;




/**
 * t_tm_scene_pic
 * 
 * 
 * @author Code-Gen
 */
public class ScenePicDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/*主键*/
	private Long 主键;
	
	/*场景ID*/
	private Long sceneId;
	
	/*图片ID*/
	private Long pictureId;
	
	
	/**
	 * 
	 * @return 主键
	 */
	public Long get主键(){
		return 主键;
	}
	
	/**
	 * 
	 * @param 主键 主键
	 */	
	public void set主键 (Long 主键) {
		this.主键 = 主键;
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
	 * @return 图片ID
	 */
	public Long getPictureId(){
		return pictureId;
	}
	
	/**
	 * 
	 * @param pictureId 图片ID
	 */	
	public void setPictureId (Long pictureId) {
		this.pictureId = pictureId;
	}
	


}
