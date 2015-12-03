package com.taw.scene.mapper;
import java.util.List;
import java.util.Map;
import com.taw.scene.domain.SceneTagDomain;
import org.apache.ibatis.annotations.Param;

/**
 * t_tm_scene_tag
 * 
 * 
 * @author Gen
 */
public interface SceneTagMapper  {

	SceneTagDomain load(@Param("id")Long id );
	
	List<SceneTagDomain> loadDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(SceneTagDomain sceneTagDomain);
	
	int delete(@Param("id")Long id );
	
	int deleteDynamic(Map<String,Object> params);
	
	int update(SceneTagDomain sceneTagDomain);
	
	int updateWithoutNull(SceneTagDomain sceneTagDomain);
	
	int update(Map<String,Object> params);
	
	


}