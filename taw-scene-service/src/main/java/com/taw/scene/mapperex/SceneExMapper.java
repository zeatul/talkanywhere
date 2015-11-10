package com.taw.scene.mapperex;

import java.util.List;
import java.util.Map;

import com.taw.scene.domain.SceneDomain;

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

}