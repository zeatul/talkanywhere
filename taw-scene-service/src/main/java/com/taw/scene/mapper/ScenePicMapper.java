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

	ScenePicDomain loadScenePic(@Param("主键")Long 主键 );
	
	List<ScenePicDomain> loadScenePicDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(ScenePicDomain scenePicDomain);
	
	int delete(@Param("主键")Long 主键 );
	
	int deleteDynamic(Map<String,Object> params);
	
	int updateScenePic(ScenePicDomain scenePicDomain);
	
	int updateScenePicWithoutNull(ScenePicDomain scenePicDomain);
	
	int update(Map<String,Object> params);
	
	


}