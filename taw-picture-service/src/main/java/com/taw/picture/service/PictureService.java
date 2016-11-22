package com.taw.picture.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hawk.pub.exception.ObjectNotFoundException;
import com.hawk.pub.exception.UnauthorizedOperateException;
import com.hawk.pub.pkgen.PkGenerator;
import com.hawk.utility.CollectionTools;
import com.hawk.utility.DateTools;
import com.hawk.utility.DomainTools;
import com.hawk.utility.JsonTools;
import com.hawk.utility.check.CheckTools;
import com.taw.picture.configure.PictureServiceConfigure;
import com.taw.picture.domain.PictureCommentDomain;
import com.taw.picture.domain.PictureDomain;
import com.taw.picture.domain.PictureThumbDomain;
import com.taw.picture.exception.PictureNotFoundException;
import com.taw.picture.exception.UploadFileNotFoundException;
import com.taw.picture.mapper.PictureCommentMapper;
import com.taw.picture.mapper.PictureMapper;
import com.taw.picture.mapper.PictureThumbMapper;
import com.taw.picture.mapperex.PictureCommentExMapper;
import com.taw.picture.mapperex.PictureExMapper;
import com.taw.pub.picture.enums.EnumPictureHotLevel;
import com.taw.pub.picture.enums.EnumPictureStatus;
import com.taw.pub.picture.enums.EnumSearchPictureOrder;
import com.taw.pub.picture.enums.EnumThumbType;
import com.taw.pub.picture.request.AddCommentParam;
import com.taw.pub.picture.request.InsrtPictureParam;
import com.taw.pub.picture.request.PictureInfoParam;
import com.taw.pub.picture.request.RemoveCommentParam;
import com.taw.pub.picture.request.RemovePictureParam;
import com.taw.pub.picture.request.SearchCommentParam;
import com.taw.pub.picture.request.SearchGlobalHotPictureParam;
import com.taw.pub.picture.request.SearchPictureAsSpecOrderParam;
import com.taw.pub.picture.request.SearchPictureSentByMyselfParam;
import com.taw.pub.picture.request.SearchSceneHotPictureParam;
import com.taw.pub.picture.request.ThumbPictureParam;
import com.taw.pub.picture.response.AddCommentResp;
import com.taw.pub.picture.response.PictureCommentInfoResp;
import com.taw.pub.picture.response.PictureInfoResp;
import com.taw.pub.picture.response.PictureStatResp;
import com.taw.user.auth.AuthThreadLocal;
import com.taw.user.domain.UserDomain;
import com.taw.user.service.UserService;

@Service
public class PictureService {
	
	@Autowired
	@Qualifier("pictureServiceConfigure")
	private PictureServiceConfigure pictureServiceConfigure;
	
	@Autowired
	private PictureThumbMapper pictureThumbMapper;
	
	@Autowired
	private PictureMapper pictureMapper;
	
	@Autowired
	private PictureCommentMapper pictureCommentMapper;
	
	@Autowired
	private PictureCommentExMapper pictureCommentExMapper;
	
	@Autowired
	private PictureExMapper pictureExMapper;
	
	@Autowired
	private UserService userService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	public void removePicture(RemovePictureParam removePictureParam) throws Exception{
		CheckTools.check(removePictureParam);
		long picId = removePictureParam.getPicId();
		long userId = removePictureParam.getUserId();
		PictureDomain pictureDomain = pictureMapper.load(picId);
		if (pictureDomain == null )
			return ;
		if (pictureDomain.getUserId() != userId)
			throw new UnauthorizedOperateException();
		pictureMapper.delete(picId);
	}
	
	
	/**
	 * 
	 * @param uuid 
	 * @return 主键
	 */
	public Long insrtPicture(InsrtPictureParam insrtPictureParam) throws Exception{
		CheckTools.check(insrtPictureParam);
		String uuid = insrtPictureParam.getUuid();
		ComputeResult computeResult = computeDir(uuid);		
		String dir = computeResult.getDir();
		String filePath = dir + File.separator + uuid;
		/**
		 * 判断文件是否存在，并取文件长度
		 */
		File file = new File(filePath);
		if (!file.exists())
			throw new UploadFileNotFoundException(uuid);
		
		long length = file.length();
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("uuid", insrtPictureParam.getUuid());
		List<PictureDomain> list = pictureMapper.loadDynamic(params);
		
		if (CollectionTools.isNotNullOrEmpty(list)){
			return list.get(0).getId();
		}
		
		/**
		 * 入库，返回主键
		 */
		PictureDomain pictureDomain = null;
		try {
			pictureDomain = new PictureDomain();
			
			pictureDomain.setUuid(uuid);
			
			pictureDomain.setSceneId(insrtPictureParam.getSceneId());
			pictureDomain.setSceneName(insrtPictureParam.getSceneName());
			
			pictureDomain.setUserId(insrtPictureParam.getUserId());
			pictureDomain.setNickname(insrtPictureParam.getNickname());
			
			pictureDomain.setCommentCount(0);		
			pictureDomain.setDownCount(0);
			pictureDomain.setUpCount(0);
			pictureDomain.setReferenceCount(1);
			pictureDomain.setSceneCount(1);
			pictureDomain.setForwardCount(0);	
			
			pictureDomain.setAppSrc(insrtPictureParam.getAppSrc().toString());
			pictureDomain.setAppSrcId(insrtPictureParam.getAppSrcId());
			
			pictureDomain.setLSize(length);
			
			pictureDomain.setStatus(EnumPictureStatus.NORMAL.toString());
			pictureDomain.setHot(EnumPictureHotLevel.NORMAL.getValue());
			
			pictureDomain.setLocation(insrtPictureParam.getLocation());
			pictureDomain.setPhotoTime(insrtPictureParam.getPhotoTime());
			
			pictureDomain.setOnScene(insrtPictureParam.getOnScene());
			
			pictureDomain.setDescription(insrtPictureParam.getDescription());
			pictureDomain.setSex(insrtPictureParam.getSex());
			
			pictureDomain.setCrdt(DateTools.now());
			pictureDomain.setId(PkGenerator.genPk());
			
			pictureMapper.insert(pictureDomain);
		} catch (DuplicateKeyException e) {
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("uuid", uuid);
			pictureDomain = pictureMapper.loadDynamic(map).get(0);
			pictureDomain.setForwardCount(pictureDomain.getForwardCount()+1);
			pictureMapper.update(pictureDomain);
		}
		
		/**
		 * 删除.success的标识文件，标识文件已经入库
		 */
		
		file = new File(filePath+".success");
		if (file.exists()){		
			file.delete();
		}
		
		return pictureDomain.getId();
		
		
	}
	
	public static class ComputeResult{
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public String getSuffix() {
			return suffix;
		}
		public void setSuffix(String suffix) {
			this.suffix = suffix;
		}
		public String getDir() {
			return dir;
		}
		public void setDir(String dir) {
			this.dir = dir;
		}
		private String suffix; //后缀
		private String dir; //完整路径
		private String path; //相对路径
	}
	
	
	/**
	 * 根据uuid ，计算 suffix 和  目录
	 * @param uuid
	 * @return
	 */
	public  ComputeResult computeDir(String uuid){
		if (StringUtils.isEmpty(uuid))
			throw new RuntimeException("uuid is null");
		uuid = uuid.trim();		
		if (uuid.length() <48)
			throw new RuntimeException("invalid uuid , uuid=yyyyMMddHHmmss_32uuid.suffix");
		
		String[] strArray = uuid.split("\\.");
		
		if (strArray.length != 2)
			throw new RuntimeException("invalid uuid , uuid=yyyyMMddHHmmss_32uuid.suffix");
		
		ComputeResult computeResult  = new ComputeResult();
		computeResult.setSuffix(strArray[1]);
		uuid = strArray[0];
		int length = uuid.length();
		strArray = new String[]{uuid.substring(length-8,length-6),
				uuid.substring(length-6,length-4),
				uuid.substring(length-4,length-2),
				uuid.substring(length-2,length)};
		
		String path = "";
		for (String str : strArray){
			path = path + "/" + computeDirPart(str);
		}
		String dir = pictureServiceConfigure.getUploadDir() + path;
		computeResult.setDir(dir);
		computeResult.setPath(path);
		return  computeResult;
		
	}
	
	/**
	 * 16进制，切分为16份
	 * @param str
	 * @return
	 */
	private static  String computeDirPart(String str){
		str = new Integer(Integer.parseInt(str, 16) % 16).toString();
		if (str.length() == 1)
			str = "0"+str;
		return str;
	}
	
	
	
	public PictureStatResp thumbPicture(ThumbPictureParam thumbPictureParam) throws Exception{
		CheckTools.check(thumbPictureParam);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("picId", thumbPictureParam.getPicId());
		params.put("userId", thumbPictureParam.getUserId());
		
		PictureDomain pictureDomain =  pictureMapper.load(thumbPictureParam.getPicId());
		if (pictureDomain == null)
			throw new PictureNotFoundException();
		
		List<PictureThumbDomain> list = pictureThumbMapper.loadDynamic(params);
		
		PictureThumbDomain pictureThumbDomainPre =null;
		if (CollectionTools.isNotNullOrEmpty(list)){
			pictureThumbDomainPre = list.get(0);
		}
		int upOffset = 0;
		int downOffset = 0;
		if (pictureThumbDomainPre != null){
			/**
			 * 删除
			 */
			pictureThumbMapper.delete(pictureThumbDomainPre.getId());
			if (EnumThumbType.UP.toString().equals(pictureThumbDomainPre.getKind())){
				upOffset = -1;
			}else
				downOffset = -1;
		}
		
		PictureThumbDomain pictureThumbDomain = new PictureThumbDomain();
		pictureThumbDomain.setKind(thumbPictureParam.getThumbType());
		pictureThumbDomain.setPicId(thumbPictureParam.getPicId());
		pictureThumbDomain.setUserId(thumbPictureParam.getUserId());
		pictureThumbDomain.setNickname(thumbPictureParam.getNickname());
		pictureThumbDomain.setCrdt(DateTools.now());
		pictureThumbDomain.setId(PkGenerator.genPk());
		
		String sex = null;
		UserDomain userDomain = userService.loadUser(pictureThumbDomain.getUserId(), true);
		if (userDomain != null){
			sex = userDomain.getSex();
		}
		pictureThumbDomain.setSex(sex);
		
		if (EnumThumbType.UP.toString().equals(thumbPictureParam.getThumbType())){
			upOffset = upOffset +1;
		}else{
			downOffset = downOffset+1;
		}
		
		/**
		 * 新增
		 */
		pictureThumbMapper.insert(pictureThumbDomain);
		
		/**
		 * 更新数量
		 */
		pictureDomain.setDownCount(pictureDomain.getDownCount() + downOffset);
		pictureDomain.setUpCount(pictureDomain.getUpCount()+upOffset);
		pictureMapper.update(pictureDomain);
		
		PictureStatResp pictureInfoResp = new PictureStatResp();
		DomainTools.copy(pictureDomain, pictureInfoResp);
		return pictureInfoResp;
		
		
	}
	
	
	public AddCommentResp addComment(AddCommentParam addCommentParam)throws Exception{
		CheckTools.check(addCommentParam);
		PictureDomain pictureDomain =  pictureMapper.load(addCommentParam.getPicId());
		if (pictureDomain == null)
			throw new Exception("Couldn't find the picture record which id = " + addCommentParam.getPicId());
		PictureCommentDomain pictureCommentDomain = new PictureCommentDomain();
		pictureCommentDomain.setContent(addCommentParam.getContent());
		
		pictureCommentDomain.setNickname(addCommentParam.getNickname());
		pictureCommentDomain.setPicId(addCommentParam.getPicId());
		pictureCommentDomain.setUserId(addCommentParam.getUserId());	
		String sex = null;
		UserDomain userDomain = userService.loadUser(addCommentParam.getUserId(), true);
		if (userDomain != null){
			sex = userDomain.getSex();
		}
		pictureCommentDomain.setSex(sex);
		pictureCommentDomain.setCrdt(DateTools.now());
		pictureCommentDomain.setId(PkGenerator.genPk());
		pictureCommentMapper.insert(pictureCommentDomain);
		
		pictureDomain.setCommentCount(pictureDomain.getCommentCount()+1);
		pictureMapper.update(pictureDomain);
		
		AddCommentResp addCommentResp = new AddCommentResp();
		
		addCommentResp.setId(pictureCommentDomain.getId());
		addCommentResp.setCrdt(pictureCommentDomain.getCrdt());
		addCommentResp.setCommentCount(pictureDomain.getCommentCount());
		addCommentResp.setUpCount(pictureDomain.getUpCount());
		addCommentResp.setDownCount(pictureDomain.getDownCount());
		addCommentResp.setForwardCount(pictureDomain.getForwardCount());
		
		return addCommentResp;
		
	}
	
	public List<PictureCommentInfoResp>  searchComment(SearchCommentParam searchCommentParam) throws Exception{
		CheckTools.check(searchCommentParam);
		List<PictureCommentDomain> pictureCommentDomainList =  pictureCommentExMapper.searchComment(searchCommentParam.getPicId(), searchCommentParam.getOffset(), searchCommentParam.getLimit());
		List<PictureCommentInfoResp> pictureCommentInfoRespList = new ArrayList<PictureCommentInfoResp>(50);
		DomainTools.copy(pictureCommentDomainList, pictureCommentInfoRespList, PictureCommentInfoResp.class);
		return pictureCommentInfoRespList;
	}
	
	public PictureStatResp removeComment(RemoveCommentParam removeCommentParam)throws Exception{
		CheckTools.check(removeCommentParam);
		PictureCommentDomain pictureCommentDomain = pictureCommentMapper.load(removeCommentParam.getCommentId());
		if (pictureCommentDomain == null)
			throw new ObjectNotFoundException(PictureCommentDomain.class);
		
		if (!removeCommentParam.getUserId().equals(pictureCommentDomain.getUserId())){
			throw new UnauthorizedOperateException();
		}
		
		PictureDomain pictureDomain =  pictureMapper.load(pictureCommentDomain.getPicId());
		if (pictureDomain == null)
			throw new PictureNotFoundException();
		
		int deletedCount = pictureCommentMapper.delete(pictureCommentDomain.getId());
		
		if (deletedCount > 0){
			pictureDomain.setCommentCount(pictureDomain.getCommentCount()-deletedCount);
			pictureMapper.update(pictureDomain);
		}
		
		PictureStatResp removeCommentResp = new PictureStatResp();
		removeCommentResp.setCommentCount(pictureDomain.getCommentCount());
		removeCommentResp.setDownCount(pictureDomain.getDownCount());
		removeCommentResp.setUpCount(pictureDomain.getUpCount());
		removeCommentResp.setForwardCount(pictureDomain.getForwardCount());
		return removeCommentResp;
	}

	public PictureInfoResp info(PictureInfoParam pictureInfoParam)throws Exception{
		CheckTools.check(pictureInfoParam);
		PictureDomain pictureDomain = pictureMapper.load(pictureInfoParam.getPicId());
		if (pictureDomain == null)
			throw new PictureNotFoundException();
		PictureInfoResp pictureInfoResp = new PictureInfoResp();
		DomainTools.copy(pictureDomain, pictureInfoResp);
		fillPath(pictureInfoResp);
		List<PictureInfoResp> pictureInfoRespList =new ArrayList<PictureInfoResp>();
		pictureInfoRespList.add(pictureInfoResp);
		fillMyUpInfo(pictureInfoRespList);
		
		logger.info("info = {}",JsonTools.toJsonString(pictureInfoRespList));
		return pictureInfoResp;
	}
	
	public void fillPath(PictureInfoResp pictureInfoResp){
		String path = computeDir(pictureInfoResp.getUuid()).getPath();
		pictureInfoResp.setUrl(pictureServiceConfigure.getUrlHead()+path+"/"+pictureInfoResp.getUuid());
		pictureInfoResp.setSurl(pictureServiceConfigure.getSurlHead()+path+"/"+pictureInfoResp.getUuid());
	}
	
	public List<PictureInfoResp> loadGlobalHotPicture(SearchGlobalHotPictureParam searchGlobalHotPictureParam) throws Exception {
		
		CheckTools.check(searchGlobalHotPictureParam);
		
		List<PictureDomain> pictureDomainList = pictureExMapper.loadGlobalHotPicture(searchGlobalHotPictureParam.getOffset(), searchGlobalHotPictureParam.getLimit());
		
		List<PictureInfoResp> pictureInfoRespList = new ArrayList<PictureInfoResp>();
		
		if (pictureDomainList != null){			
			for (PictureDomain pictureDomain : pictureDomainList){				
				PictureInfoResp pictureInfoResp = new PictureInfoResp();
				DomainTools.copy(pictureDomain, pictureInfoResp);
				fillPath(pictureInfoResp);
				pictureInfoRespList.add(pictureInfoResp);
			}
		}
		fillMyUpInfo(pictureInfoRespList);
		
		logger.info("loadGlobalHotPicture = {}",JsonTools.toJsonString(pictureInfoRespList));
		return pictureInfoRespList;
	}
	
	public List<PictureInfoResp> loadSceneHotPicture(SearchSceneHotPictureParam searchSceneHotPictureParam) throws Exception{
		CheckTools.check(searchSceneHotPictureParam);
		List<PictureDomain> pictureDomainList = pictureExMapper.loadSceneHotPicture(searchSceneHotPictureParam.getSceneId(),searchSceneHotPictureParam.getOffset(), searchSceneHotPictureParam.getLimit());
		List<PictureInfoResp> pictureInfoRespList = new ArrayList<PictureInfoResp>();		
		if (pictureDomainList != null){			
			for (PictureDomain pictureDomain : pictureDomainList){				
				PictureInfoResp pictureInfoResp = new PictureInfoResp();
				DomainTools.copy(pictureDomain, pictureInfoResp);
				fillPath(pictureInfoResp);
				pictureInfoRespList.add(pictureInfoResp);
			}			
		}	
		fillMyUpInfo(pictureInfoRespList);
		
		logger.info("loadSceneHotPicture = {}",JsonTools.toJsonString(pictureInfoRespList));
		return pictureInfoRespList;
	}
	
	public List<PictureInfoResp> loadSceneCrdtDescPicture(SearchSceneHotPictureParam searchSceneHotPictureParam) throws Exception{
		CheckTools.check(searchSceneHotPictureParam);
		List<PictureDomain> pictureDomainList = pictureExMapper.loadSceneCrdtDescPicture(searchSceneHotPictureParam.getSceneId(),searchSceneHotPictureParam.getOffset(), searchSceneHotPictureParam.getLimit());
		List<PictureInfoResp> pictureInfoRespList = new ArrayList<PictureInfoResp>();		
		if (pictureDomainList != null){			
			for (PictureDomain pictureDomain : pictureDomainList){				
				PictureInfoResp pictureInfoResp = new PictureInfoResp();
				DomainTools.copy(pictureDomain, pictureInfoResp);
				fillPath(pictureInfoResp);
				pictureInfoRespList.add(pictureInfoResp);
			}
		}	
		fillMyUpInfo(pictureInfoRespList);
		logger.info("loadSceneCrdtDescPicture = {}",JsonTools.toJsonString(pictureInfoRespList));
		return pictureInfoRespList;
	}
	
	public List<PictureInfoResp> loadScenePictureAsSpecOrder(SearchPictureAsSpecOrderParam searchPictureAsSpecOrderParam) throws Exception{
		CheckTools.check(searchPictureAsSpecOrderParam);
		List<PictureDomain> pictureDomainList = pictureExMapper.loadScenePictureAsSpecOrder(searchPictureAsSpecOrderParam.getSceneId()
				,searchPictureAsSpecOrderParam.getOffset(), searchPictureAsSpecOrderParam.getLimit(),EnumSearchPictureOrder.toOrderByString(searchPictureAsSpecOrderParam.getOrderBy()));
		List<PictureInfoResp> pictureInfoRespList = new ArrayList<PictureInfoResp>();		
		if (pictureDomainList != null){			
			for (PictureDomain pictureDomain : pictureDomainList){				
				PictureInfoResp pictureInfoResp = new PictureInfoResp();
				DomainTools.copy(pictureDomain, pictureInfoResp);
				fillPath(pictureInfoResp);
				pictureInfoRespList.add(pictureInfoResp);
			}
		}	
		fillMyUpInfo(pictureInfoRespList);
		logger.info("loadScenePictureAsSpecOrder = {}",JsonTools.toJsonString(pictureInfoRespList));
		return pictureInfoRespList;
	}
	
	public List<PictureInfoResp> loadPictureSentByMyself(SearchPictureSentByMyselfParam searchPictureSentByMyselfParam) throws Exception{
		CheckTools.check(searchPictureSentByMyselfParam);
		List<PictureDomain> pictureDomainList = pictureExMapper.loadPictureSentByMyself(searchPictureSentByMyselfParam.getUserId(),searchPictureSentByMyselfParam.getOffset(), searchPictureSentByMyselfParam.getLimit());
		List<PictureInfoResp> pictureInfoRespList = new ArrayList<PictureInfoResp>();		
		if (pictureDomainList != null){			
			for (PictureDomain pictureDomain : pictureDomainList){				
				PictureInfoResp pictureInfoResp = new PictureInfoResp();
				DomainTools.copy(pictureDomain, pictureInfoResp);
				fillPath(pictureInfoResp);
				pictureInfoRespList.add(pictureInfoResp);
			}
		}	
		fillMyUpInfo(pictureInfoRespList);
		logger.info("loadPictureSentByMyself = {}",JsonTools.toJsonString(pictureInfoRespList));
		return pictureInfoRespList;
	}
	
	/**
	 * 填写我的点赞信息
	 */
	private void fillMyUpInfo(List<PictureInfoResp> pictureInfoRespList ){
		
		if (pictureInfoRespList == null || pictureInfoRespList.size() ==0)
			return ;
		
		Long userId = AuthThreadLocal.getUserId();
		if (userId == null)
			return;
		Map<String,Object> params = new HashMap<String,Object>();
		
		params.put("userId", userId);		
		
		List<PictureThumbDomain> list = pictureThumbMapper.loadDynamic(params);
		
		if (list == null || list.size() == 0)
			return;
		
		Map<Long,PictureThumbDomain> map = new HashMap<Long,PictureThumbDomain>();
		for (PictureThumbDomain pictureThumbDomain : list){
			map.put(pictureThumbDomain.getPicId(), pictureThumbDomain);
		}
		
		for (PictureInfoResp pictureInfoResp :pictureInfoRespList){
			PictureThumbDomain pictureThumbDomain = map.get(pictureInfoResp.getId());
			if (pictureThumbDomain != null){
				pictureInfoResp.setUp(pictureThumbDomain.getKind());
			}
		}
	}
}
