package com.taw.scene.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hawk.pub.pkgen.PkGenerator;
import com.taw.pub.scene.enums.EnumMessageType;
import com.taw.scene.domain.ScenePicDomain;
import com.taw.scene.mapper.ScenePicMapper;

@Service
public class ScenePicService {
	
	@Autowired
	private ScenePicMapper scenePicMapper;
	
	
	public void inserScenePic(Long mid, Long sceneId, String sceneName,EnumMessageType messageType,Long picId,String picUuid){
		ScenePicDomain scenePicDomain = new ScenePicDomain();
		scenePicDomain.setKind(messageType.toString());
		scenePicDomain.setMid(mid);
		scenePicDomain.setSceneId(sceneId);
		scenePicDomain.setSceneName(sceneName);
		scenePicDomain.setPicId(picId);
		scenePicDomain.setPicUuid(picUuid);
		scenePicDomain.setId(PkGenerator.genPk());
		scenePicMapper.insert(scenePicDomain);
	}

}
