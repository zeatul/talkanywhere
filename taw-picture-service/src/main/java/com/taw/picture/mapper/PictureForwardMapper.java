package com.taw.picture.mapper;
import java.util.List;
import java.util.Map;
import com.taw.picture.domain.PictureForwardDomain;
import org.apache.ibatis.annotations.Param;

/**
 * t_pm_picture_forward
 * 
 * 
 * @author Gen
 */
public interface PictureForwardMapper  {

	PictureForwardDomain load(@Param("id")Long id );
	
	List<PictureForwardDomain> loadDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(PictureForwardDomain pictureForwardDomain);
	
	int delete(@Param("id")Long id );
	
	int deleteDynamic(Map<String,Object> params);
	
	int update(PictureForwardDomain pictureForwardDomain);
	
	int updateWithoutNull(PictureForwardDomain pictureForwardDomain);
	
	int update(Map<String,Object> params);
	
	


}