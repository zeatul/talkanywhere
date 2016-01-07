package com.taw.picture.mapper;
import java.util.List;
import java.util.Map;
import com.taw.picture.domain.PictureDomain;
import org.apache.ibatis.annotations.Param;

/**
 * t_pm_picture
 * 
 * 
 * @author Gen
 */
public interface PictureMapper  {

	PictureDomain load(@Param("id")Long id );
	
	List<PictureDomain> loadDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(PictureDomain pictureDomain);
	
	int delete(@Param("id")Long id );
	
	int deleteDynamic(Map<String,Object> params);
	
	int update(PictureDomain pictureDomain);
	
	int updateWithoutNull(PictureDomain pictureDomain);
	
	int update(Map<String,Object> params);
	
	


}