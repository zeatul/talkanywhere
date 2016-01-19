package com.taw.scene.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import com.hawk.pub.pkgen.PkGenerator;
import com.taw.pub.scene.enums.EnumMessageType;
import com.taw.scene.domain.ScenePicDomain;
import com.taw.scene.mapper.ScenePicMapper;

@Service
public class ScenePicService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ScenePicMapper scenePicMapper;
	
	
	public void inserScenePic(Long mid, Long sceneId, String sceneName,EnumMessageType messageType,Long picId,String picUuid){
		try {
			ScenePicDomain scenePicDomain = new ScenePicDomain();
			scenePicDomain.setKind(messageType.toString());
			scenePicDomain.setMid(mid);
			scenePicDomain.setSceneId(sceneId);
			scenePicDomain.setSceneName(sceneName);
			scenePicDomain.setPicId(picId);
			scenePicDomain.setPicUuid(picUuid);
			scenePicDomain.setId(PkGenerator.genPk());
			scenePicMapper.insert(scenePicDomain);
		} catch (DuplicateKeyException e) {
			logger.error("error",e);
		}
	}

}
