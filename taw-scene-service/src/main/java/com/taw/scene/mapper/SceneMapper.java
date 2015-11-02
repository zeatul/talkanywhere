package com.taw.scene.mapper;
import java.util.List;
import java.util.Map;
import com.taw.scene.domain.SceneDomain;
import org.apache.ibatis.annotations.Param;

/**
 * t_tm_scene
 * 
 * 
 * @author Gen
 */
public interface SceneMapper  {

	SceneDomain loadScene(@Param("id")Long id );
	
	List<SceneDomain> loadSceneDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(SceneDomain sceneDomain);
	
	int delete(@Param("id")Long id );
	
	int deleteDynamic(Map<String,Object> params);
	
	int updateScene(SceneDomain sceneDomain);
	
	int updateSceneWithoutNull(SceneDomain sceneDomain);
	
	int update(Map<String,Object> params);
	
	


}