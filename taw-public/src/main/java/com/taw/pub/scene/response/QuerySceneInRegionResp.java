package com.taw.pub.scene.response;

import java.util.List;

public class QuerySceneInRegionResp {
	
	public Integer getSceneCount() {
		return sceneCount;
	}

	public void setSceneCount(Integer sceneCount) {
		this.sceneCount = sceneCount;
	}

	public List<FuzziedSceneResp> getFuzziedSceneResps() {
		return fuzziedSceneResps;
	}

	public void setFuzziedSceneResps(List<FuzziedSceneResp> fuzziedSceneResps) {
		this.fuzziedSceneResps = fuzziedSceneResps;
	}

	public List<SceneResp> getSceneResps() {
		return sceneResps;
	}

	public void setSceneResps(List<SceneResp> sceneResps) {
		this.sceneResps = sceneResps;
	}

	public Integer getFuzziedSceneCount() {
		return fuzziedSceneCount;
	}

	public void setFuzziedSceneCount(Integer fuzziedSceneCount) {
		this.fuzziedSceneCount = fuzziedSceneCount;
	}

	

	/**
	 * 模糊化场景列表
	 */
	private List<FuzziedSceneResp> fuzziedSceneResps;
	
	/**
	 * 普通场景列表
	 */
	private List<SceneResp> sceneResps;
	
	/**
	 * 模糊化场景数量
	 */
	private Integer fuzziedSceneCount;
	
	/**
	 * 普通场景数量
	 */
	private Integer sceneCount;

}
