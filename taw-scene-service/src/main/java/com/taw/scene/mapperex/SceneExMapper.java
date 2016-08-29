package com.taw.scene.mapperex;

import java.util.List;

import com.taw.scene.domain.SceneDomain;
import com.taw.scene.domainex.FuzziedSceneDomain;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author pzhang1
 * 
 */
public interface SceneExMapper {

	/**
	 * 查询指定矩形范围内的场景
	 * @param minLng
	 * @param maxLng
	 * @param minLat
	 * @param maxLat
	 * @return
	 */
	public List<SceneDomain> querySceneInRegion(@Param("minLng") java.math.BigDecimal minLng, @Param("maxLng") java.math.BigDecimal maxLng,
			@Param("minLat") java.math.BigDecimal minLat, @Param("maxLat") java.math.BigDecimal maxLat);
	
	/**
	 * 查询指定矩形范围内的场景数量
	 * @param minLng
	 * @param maxLng
	 * @param minLat
	 * @param maxLat
	 * @return
	 */
	public Integer countSceneInRegion(@Param("minLng") java.math.BigDecimal minLng, @Param("maxLng") java.math.BigDecimal maxLng,
			@Param("minLat") java.math.BigDecimal minLat, @Param("maxLat") java.math.BigDecimal maxLat);


	/**
	 * 查询在场人数
	 * @param sceneId
	 * @return
	 */
	public Integer queryEnterCount(@Param("sceneId") Long sceneId);
	
	/**
	 * 根据名称查询场景
	 * @param name
	 * @return
	 */
	public List<SceneDomain> querySceneByName(@Param("name") String name,@Param("offset")Integer offset , @Param("limit")Integer limit);
	
	/**
	 * 按照城市分组模糊化计算
	 * @return
	 */
	public List<FuzziedSceneDomain> querySceneGroupByCity(@Param("minLng") java.math.BigDecimal minLng, @Param("maxLng") java.math.BigDecimal maxLng,
			@Param("minLat") java.math.BigDecimal minLat, @Param("maxLat") java.math.BigDecimal maxLat);
	
	/**
	 * 按照密度做模糊化
	 * @param minLng
	 * @param maxLng
	 * @param minLat
	 * @param maxLat
	 * @return
	 */
	public List<FuzziedSceneDomain> queryFuzziedScene(@Param("minLng") java.math.BigDecimal minLng, @Param("maxLng") java.math.BigDecimal maxLng,
			@Param("minLat") java.math.BigDecimal minLat, @Param("maxLat") java.math.BigDecimal maxLat,  @Param("density") java.math.BigDecimal density);
	
}