package com.taw.picture.mapper;
import java.util.List;
import java.util.Map;
import com.taw.picture.domain.PictureCommentDomain;
import org.apache.ibatis.annotations.Param;

/**
 * t_pm_picture_comment
 * 
 * 
 * @author Gen
 */
public interface PictureCommentMapper  {

	PictureCommentDomain load(@Param("id")Long id );
	
	List<PictureCommentDomain> loadDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(PictureCommentDomain pictureCommentDomain);
	
	int delete(@Param("id")Long id );
	
	int deleteDynamic(Map<String,Object> params);
	
	int update(PictureCommentDomain pictureCommentDomain);
	
	int updateWithoutNull(PictureCommentDomain pictureCommentDomain);
	
	int update(Map<String,Object> params);
	
	


}