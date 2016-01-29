package com.taw.picture.mapperex;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.taw.picture.domain.PictureDomain;

/**
 * t_pm_picture
 * 
 * 
 * @author Gen
 */
public interface PictureExMapper  {

	
	
	List<PictureDomain> loadGlobalHotPicture(@Param("offset")Integer offset , @Param("limit")Integer limit);
	
	
	List<PictureDomain> loadSceneHotPicture(@Param("sceneId") Long sceneId,@Param("offset")Integer offset , @Param("limit")Integer limit);
	
	
	


}