package com.taw.picture.mapper;
import java.util.List;
import java.util.Map;
import com.taw.picture.domain.PictureThumbDomain;
import org.apache.ibatis.annotations.Param;

/**
 * t_pm_picture_thumb
 * 
 * 
 * @author Gen
 */
public interface PictureThumbMapper  {

	PictureThumbDomain load(@Param("id")Long id );
	
	List<PictureThumbDomain> loadDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(PictureThumbDomain pictureThumbDomain);
	
	int delete(@Param("id")Long id );
	
	int deleteDynamic(Map<String,Object> params);
	
	int update(PictureThumbDomain pictureThumbDomain);
	
	int updateWithoutNull(PictureThumbDomain pictureThumbDomain);
	
	int update(Map<String,Object> params);
	
	


}