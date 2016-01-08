package com.taw.scene.mapper;
import java.util.List;
import java.util.Map;
import com.taw.scene.domain.ScenePicDomain;
import org.apache.ibatis.annotations.Param;

/**
 * t_tm_scene_pic
 * 
 * 
 * @author Gen
 */
public interface ScenePicMapper  {

	ScenePicDomain load(@Param("id")Long id );
	
	List<ScenePicDomain> loadDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(ScenePicDomain scenePicDomain);
	
	int delete(@Param("id")Long id );
	
	int deleteDynamic(Map<String,Object> params);
	
	int update(ScenePicDomain scenePicDomain);
	
	int updateWithoutNull(ScenePicDomain scenePicDomain);
	
	int update(Map<String,Object> params);
	
	


}