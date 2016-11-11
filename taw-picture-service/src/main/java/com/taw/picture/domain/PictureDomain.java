package com.taw.picture.domain;
import java.io.Serializable;
import java.util.Date;



/**
 * t_pm_picture
 * 
 * 
 * @author Code-Gen
 */
public class PictureDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/*主键*/
	private Long id;
	
	/*UUID*/
	private String uuid;
	
	/*上传者ID*/
	private Long userId;
	
	/*上传者昵称*/
	private String nickname;
	
	/*场景Id*/
	private Long sceneId;
	
	/*场景名称*/
	private String sceneName;
	
	/*图片状态*/
	private String status;
	
	/*拍摄地点*/
	private String location;
	
	/*拍摄时间*/
	private Date photoTime;
	
	/*小图片地址*/
	private String sUrl;
	
	/*中图片地址*/
	private String mUrl;
	
	/*大图片地址*/
	private String lUrl;
	
	/*内部关联引用计数*/
	private Integer referenceCount;
	
	/*热门图片*/
	private Integer hot;
	
	/*点赞数*/
	private Integer upCount;
	
	/*不支持数目*/
	private Integer downCount;
	
	/*评论数*/
	private Integer commentCount;
	
	/*转发数*/
	private Integer forwardCount;
	
	/*场景数*/
	private Integer sceneCount;
	
	/*小图片大小*/
	private Long sSize;
	
	/*中图片大小*/
	private Long mSize;
	
	/*大图片大小*/
	private Long lSize;
	
	/*创建时间*/
	private Date crdt;
	
	/**/
	private String kind;
	
	/**/
	private String suffix;
	
	/**/
	private String appSrc;
	
	/**/
	private Short onScene;
	
	/**/
	private String description;
	
	/*应用来源ID*/
	private Long appSrcId;
	
	/*用户性别*/
	private String sex;
	
	
	/**
	 * 
	 * @return 主键
	 */
	public Long getId(){
		return id;
	}
	
	/**
	 * 
	 * @param id 主键
	 */	
	public void setId (Long id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return UUID
	 */
	public String getUuid(){
		return uuid;
	}
	
	/**
	 * 
	 * @param uuid UUID
	 */	
	public void setUuid (String uuid) {
		this.uuid = uuid;
	}
	
	/**
	 * 
	 * @return 上传者ID
	 */
	public Long getUserId(){
		return userId;
	}
	
	/**
	 * 
	 * @param userId 上传者ID
	 */	
	public void setUserId (Long userId) {
		this.userId = userId;
	}
	
	/**
	 * 
	 * @return 上传者昵称
	 */
	public String getNickname(){
		return nickname;
	}
	
	/**
	 * 
	 * @param nickname 上传者昵称
	 */	
	public void setNickname (String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * 
	 * @return 场景Id
	 */
	public Long getSceneId(){
		return sceneId;
	}
	
	/**
	 * 
	 * @param sceneId 场景Id
	 */	
	public void setSceneId (Long sceneId) {
		this.sceneId = sceneId;
	}
	
	/**
	 * 
	 * @return 场景名称
	 */
	public String getSceneName(){
		return sceneName;
	}
	
	/**
	 * 
	 * @param sceneName 场景名称
	 */	
	public void setSceneName (String sceneName) {
		this.sceneName = sceneName;
	}
	
	/**
	 * 
	 * @return 图片状态
	 */
	public String getStatus(){
		return status;
	}
	
	/**
	 * 
	 * @param status 图片状态
	 */	
	public void setStatus (String status) {
		this.status = status;
	}
	
	/**
	 * 
	 * @return 拍摄地点
	 */
	public String getLocation(){
		return location;
	}
	
	/**
	 * 
	 * @param location 拍摄地点
	 */	
	public void setLocation (String location) {
		this.location = location;
	}
	
	/**
	 * 
	 * @return 拍摄时间
	 */
	public Date getPhotoTime(){
		return photoTime;
	}
	
	/**
	 * 
	 * @param photoTime 拍摄时间
	 */	
	public void setPhotoTime (Date photoTime) {
		this.photoTime = photoTime;
	}
	
	/**
	 * 
	 * @return 小图片地址
	 */
	public String getSUrl(){
		return sUrl;
	}
	
	/**
	 * 
	 * @param sUrl 小图片地址
	 */	
	public void setSUrl (String sUrl) {
		this.sUrl = sUrl;
	}
	
	/**
	 * 
	 * @return 中图片地址
	 */
	public String getMUrl(){
		return mUrl;
	}
	
	/**
	 * 
	 * @param mUrl 中图片地址
	 */	
	public void setMUrl (String mUrl) {
		this.mUrl = mUrl;
	}
	
	/**
	 * 
	 * @return 大图片地址
	 */
	public String getLUrl(){
		return lUrl;
	}
	
	/**
	 * 
	 * @param lUrl 大图片地址
	 */	
	public void setLUrl (String lUrl) {
		this.lUrl = lUrl;
	}
	
	/**
	 * 
	 * @return 内部关联引用计数
	 */
	public Integer getReferenceCount(){
		return referenceCount;
	}
	
	/**
	 * 
	 * @param referenceCount 内部关联引用计数
	 */	
	public void setReferenceCount (Integer referenceCount) {
		this.referenceCount = referenceCount;
	}
	
	/**
	 * 
	 * @return 热门图片
	 */
	public Integer getHot(){
		return hot;
	}
	
	/**
	 * 
	 * @param hot 热门图片
	 */	
	public void setHot (Integer hot) {
		this.hot = hot;
	}
	
	/**
	 * 
	 * @return 点赞数
	 */
	public Integer getUpCount(){
		return upCount;
	}
	
	/**
	 * 
	 * @param upCount 点赞数
	 */	
	public void setUpCount (Integer upCount) {
		this.upCount = upCount;
	}
	
	/**
	 * 
	 * @return 不支持数目
	 */
	public Integer getDownCount(){
		return downCount;
	}
	
	/**
	 * 
	 * @param downCount 不支持数目
	 */	
	public void setDownCount (Integer downCount) {
		this.downCount = downCount;
	}
	
	/**
	 * 
	 * @return 评论数
	 */
	public Integer getCommentCount(){
		return commentCount;
	}
	
	/**
	 * 
	 * @param commentCount 评论数
	 */	
	public void setCommentCount (Integer commentCount) {
		this.commentCount = commentCount;
	}
	
	/**
	 * 
	 * @return 转发数
	 */
	public Integer getForwardCount(){
		return forwardCount;
	}
	
	/**
	 * 
	 * @param forwardCount 转发数
	 */	
	public void setForwardCount (Integer forwardCount) {
		this.forwardCount = forwardCount;
	}
	
	/**
	 * 
	 * @return 场景数
	 */
	public Integer getSceneCount(){
		return sceneCount;
	}
	
	/**
	 * 
	 * @param sceneCount 场景数
	 */	
	public void setSceneCount (Integer sceneCount) {
		this.sceneCount = sceneCount;
	}
	
	/**
	 * 
	 * @return 小图片大小
	 */
	public Long getSSize(){
		return sSize;
	}
	
	/**
	 * 
	 * @param sSize 小图片大小
	 */	
	public void setSSize (Long sSize) {
		this.sSize = sSize;
	}
	
	/**
	 * 
	 * @return 中图片大小
	 */
	public Long getMSize(){
		return mSize;
	}
	
	/**
	 * 
	 * @param mSize 中图片大小
	 */	
	public void setMSize (Long mSize) {
		this.mSize = mSize;
	}
	
	/**
	 * 
	 * @return 大图片大小
	 */
	public Long getLSize(){
		return lSize;
	}
	
	/**
	 * 
	 * @param lSize 大图片大小
	 */	
	public void setLSize (Long lSize) {
		this.lSize = lSize;
	}
	
	/**
	 * 
	 * @return 创建时间
	 */
	public Date getCrdt(){
		return crdt;
	}
	
	/**
	 * 
	 * @param crdt 创建时间
	 */	
	public void setCrdt (Date crdt) {
		this.crdt = crdt;
	}
	
	/**
	 * 
	 * @return 
	 */
	public String getKind(){
		return kind;
	}
	
	/**
	 * 
	 * @param kind 
	 */	
	public void setKind (String kind) {
		this.kind = kind;
	}
	
	/**
	 * 
	 * @return 
	 */
	public String getSuffix(){
		return suffix;
	}
	
	/**
	 * 
	 * @param suffix 
	 */	
	public void setSuffix (String suffix) {
		this.suffix = suffix;
	}
	
	/**
	 * 
	 * @return 
	 */
	public String getAppSrc(){
		return appSrc;
	}
	
	/**
	 * 
	 * @param appSrc 
	 */	
	public void setAppSrc (String appSrc) {
		this.appSrc = appSrc;
	}
	
	/**
	 * 
	 * @return 
	 */
	public Short getOnScene(){
		return onScene;
	}
	
	/**
	 * 
	 * @param onScene 
	 */	
	public void setOnScene (Short onScene) {
		this.onScene = onScene;
	}
	
	/**
	 * 
	 * @return 
	 */
	public String getDescription(){
		return description;
	}
	
	/**
	 * 
	 * @param description 
	 */	
	public void setDescription (String description) {
		this.description = description;
	}
	
	/**
	 * 
	 * @return 应用来源ID
	 */
	public Long getAppSrcId(){
		return appSrcId;
	}
	
	/**
	 * 
	 * @param appSrcId 应用来源ID
	 */	
	public void setAppSrcId (Long appSrcId) {
		this.appSrcId = appSrcId;
	}
	
	/**
	 * 
	 * @return 用户性别
	 */
	public String getSex(){
		return sex;
	}
	
	/**
	 * 
	 * @param sex 用户性别
	 */	
	public void setSex (String sex) {
		this.sex = sex;
	}
	


}
