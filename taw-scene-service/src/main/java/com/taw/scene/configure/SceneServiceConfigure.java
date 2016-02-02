package com.taw.scene.configure;

public class SceneServiceConfigure {
	
	public Integer getMaxSceneCountOfQueryOnRegion() {
		return maxSceneCountOfQueryOnRegion;
	}

	public void setMaxSceneCountOfQueryOnRegion(Integer maxSceneCountOfQueryOnRegion) {
		this.maxSceneCountOfQueryOnRegion = maxSceneCountOfQueryOnRegion;
	}

	/**
	 * 单次按区域查询的最大允许场景数量,超过该数量，需要做聚合
	 */
	private Integer maxSceneCountOfQueryOnRegion = 100;
	
	

}
