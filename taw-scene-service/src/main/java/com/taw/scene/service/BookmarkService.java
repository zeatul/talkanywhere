package com.taw.scene.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.hawk.pub.mybatis.SqlParamHelper;
import com.hawk.pub.pkgen.PkGenerator;
import com.hawk.utility.DateTools;
import com.hawk.utility.check.CheckTools;
import com.taw.pub.scene.enums.EnumAddBookmarkRespResult;
import com.taw.pub.scene.request.AddBookmarkParam;
import com.taw.pub.scene.request.QueryBookmarkParam;
import com.taw.pub.scene.request.RemoveBookmarkParam;
import com.taw.pub.scene.response.AddBookmarkResp;
import com.taw.scene.domain.BookmarkDomain;
import com.taw.scene.domain.SceneDomain;
import com.taw.scene.mapper.BookmarkMapper;


@Service
public class BookmarkService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SceneService sceneService;
	
	@Autowired
	private BookmarkMapper bookmarkMapper;

	public List<AddBookmarkResp> add(AddBookmarkParam addBookmarkParam ) throws Exception{
		CheckTools.check(addBookmarkParam);
		Long userId = addBookmarkParam.getUserId();
		
		List<AddBookmarkResp> result = new ArrayList<AddBookmarkResp>(addBookmarkParam.getSceneIds().size());
		
		for (Long sceneId : addBookmarkParam.getSceneIds()){
			AddBookmarkResp  addBookmarkResp  = new AddBookmarkResp();
			addBookmarkResp.setSceneId(sceneId);
			result.add(addBookmarkResp);
			SceneDomain sceneDomain = sceneService.loadSceneDomain(sceneId, true);
			if (sceneDomain == null){
				addBookmarkResp.setResult(EnumAddBookmarkRespResult.SCENE_NOTEXIST.getValue());
				continue;
			}
				
			
			try {
				BookmarkDomain bookmarkDomain = new BookmarkDomain();
				bookmarkDomain.setBookTime(DateTools.now());
				bookmarkDomain.setSceneId(sceneId);
				bookmarkDomain.setSceneName(sceneDomain.getName());
				bookmarkDomain.setUserId(userId);
				bookmarkDomain.setId(PkGenerator.genPk());
				bookmarkMapper.insert(bookmarkDomain);
				addBookmarkResp.setResult(EnumAddBookmarkRespResult.BOOKED.getValue());
			} catch (DuplicateKeyException e) {
				addBookmarkResp.setResult(EnumAddBookmarkRespResult.BOOKED_BEFORE.getValue());
				logger.error("The Scene={}"+sceneId +" is booked before",e);
			}
		}
		
		return result;
	}
	
	public List<BookmarkDomain> search(QueryBookmarkParam queryBookmarkParam) throws Exception{
		CheckTools.check(queryBookmarkParam);
		Map<String,Object> params = SqlParamHelper.generatePageParams("book_time desc", queryBookmarkParam.getOffset(),queryBookmarkParam.getLimit());
		params.put("userId", queryBookmarkParam.getUserId());
		return bookmarkMapper.loadDynamic(params);
	}
	

	private Map<String,Object> genDelParam(Long userId, Long sceneId){
		Map<String,Object> params = new HashMap<String, Object>();
		
		if (sceneId == null || userId == null)
			throw new RuntimeException("The userId or coUserId can't be null");
		
		params.put("userId", userId);
		params.put("sceneId", sceneId);
		return params;
	}
	
	public void remove(RemoveBookmarkParam removeBookmarkParam) throws Exception{
		CheckTools.check(removeBookmarkParam);
		Long userId = removeBookmarkParam.getUserId();
		for (Long sceneId : removeBookmarkParam.getSceneIds()){
			bookmarkMapper.deleteDynamic(genDelParam(userId,sceneId));
		}
	}
}
