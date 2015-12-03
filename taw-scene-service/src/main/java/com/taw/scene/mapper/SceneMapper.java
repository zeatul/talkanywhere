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

	SceneDomain load(@Param("id")Long id );
	
	List<SceneDomain> loadDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(SceneDomain sceneDomain);
	
	int delete(@Param("id")Long id );
	
	int deleteDynamic(Map<String,Object> params);
	
	int update(SceneDomain sceneDomain);
	
	int updateWithoutNull(SceneDomain sceneDomain);
	
	int update(Map<String,Object> params);
	
	


}