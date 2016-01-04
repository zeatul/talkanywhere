package com.taw.scene.domain;
import java.io.Serializable;




/**
 * t_tm_scene
 * 
 * 
 * @author Code-Gen
 */
public class SceneDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/*主键*/
	private Long id;
	
	/*场景名称*/
	private String name;
	
	/*场景类型*/
	private String kind;
	
	/*半径(米)*/
	private Integer radius;
	
	/*经度*/
	private java.math.BigDecimal centerLng;
	
	/*纬度*/
	private java.math.BigDecimal centerLat;
	
	/*经度*/
	private java.math.BigDecimal leftTopLng;
	
	/*纬度*/
	private java.math.BigDecimal leftTopLat;
	
	/*经度*/
	private java.math.BigDecimal leftBottomLng;
	
	/*纬度*/
	private java.math.BigDecimal leftBottomLat;
	
	/*经度*/
	private java.math.BigDecimal rightTopLng;
	
	/*纬度*/
	private java.math.BigDecimal rightTopLat;
	
	/*经度*/
	private java.math.BigDecimal rightBottomLng;
	
	/*纬度*/
	private java.math.BigDecimal rightBottomLat;
	
	/*国家*/
	private Long country;
	
	/*省*/
	private Long province;
	
	/*市*/
	private Long city;
	
	/*区县*/
	private Long county;
	
	/*乡镇*/
	private Long town;
	
	/*地区/商圈*/
	private Long region;
	
	/*全地址*/
	private String address;
	
	
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
	 * @return 场景名称
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * 
	 * @param name 场景名称
	 */	
	public void setName (String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return 场景类型
	 */
	public String getKind(){
		return kind;
	}
	
	/**
	 * 
	 * @param kind 场景类型
	 */	
	public void setKind (String kind) {
		this.kind = kind;
	}
	
	/**
	 * 
	 * @return 半径(米)
	 */
	public Integer getRadius(){
		return radius;
	}
	
	/**
	 * 
	 * @param radius 半径(米)
	 */	
	public void setRadius (Integer radius) {
		this.radius = radius;
	}
	
	/**
	 * 
	 * @return 经度
	 */
	public java.math.BigDecimal getCenterLng(){
		return centerLng;
	}
	
	/**
	 * 
	 * @param centerLng 经度
	 */	
	public void setCenterLng (java.math.BigDecimal centerLng) {
		this.centerLng = centerLng;
	}
	
	/**
	 * 
	 * @return 纬度
	 */
	public java.math.BigDecimal getCenterLat(){
		return centerLat;
	}
	
	/**
	 * 
	 * @param centerLat 纬度
	 */	
	public void setCenterLat (java.math.BigDecimal centerLat) {
		this.centerLat = centerLat;
	}
	
	/**
	 * 
	 * @return 经度
	 */
	public java.math.BigDecimal getLeftTopLng(){
		return leftTopLng;
	}
	
	/**
	 * 
	 * @param leftTopLng 经度
	 */	
	public void setLeftTopLng (java.math.BigDecimal leftTopLng) {
		this.leftTopLng = leftTopLng;
	}
	
	/**
	 * 
	 * @return 纬度
	 */
	public java.math.BigDecimal getLeftTopLat(){
		return leftTopLat;
	}
	
	/**
	 * 
	 * @param leftTopLat 纬度
	 */	
	public void setLeftTopLat (java.math.BigDecimal leftTopLat) {
		this.leftTopLat = leftTopLat;
	}
	
	/**
	 * 
	 * @return 经度
	 */
	public java.math.BigDecimal getLeftBottomLng(){
		return leftBottomLng;
	}
	
	/**
	 * 
	 * @param leftBottomLng 经度
	 */	
	public void setLeftBottomLng (java.math.BigDecimal leftBottomLng) {
		this.leftBottomLng = leftBottomLng;
	}
	
	/**
	 * 
	 * @return 纬度
	 */
	public java.math.BigDecimal getLeftBottomLat(){
		return leftBottomLat;
	}
	
	/**
	 * 
	 * @param leftBottomLat 纬度
	 */	
	public void setLeftBottomLat (java.math.BigDecimal leftBottomLat) {
		this.leftBottomLat = leftBottomLat;
	}
	
	/**
	 * 
	 * @return 经度
	 */
	public java.math.BigDecimal getRightTopLng(){
		return rightTopLng;
	}
	
	/**
	 * 
	 * @param rightTopLng 经度
	 */	
	public void setRightTopLng (java.math.BigDecimal rightTopLng) {
		this.rightTopLng = rightTopLng;
	}
	
	/**
	 * 
	 * @return 纬度
	 */
	public java.math.BigDecimal getRightTopLat(){
		return rightTopLat;
	}
	
	/**
	 * 
	 * @param rightTopLat 纬度
	 */	
	public void setRightTopLat (java.math.BigDecimal rightTopLat) {
		this.rightTopLat = rightTopLat;
	}
	
	/**
	 * 
	 * @return 经度
	 */
	public java.math.BigDecimal getRightBottomLng(){
		return rightBottomLng;
	}
	
	/**
	 * 
	 * @param rightBottomLng 经度
	 */	
	public void setRightBottomLng (java.math.BigDecimal rightBottomLng) {
		this.rightBottomLng = rightBottomLng;
	}
	
	/**
	 * 
	 * @return 纬度
	 */
	public java.math.BigDecimal getRightBottomLat(){
		return rightBottomLat;
	}
	
	/**
	 * 
	 * @param rightBottomLat 纬度
	 */	
	public void setRightBottomLat (java.math.BigDecimal rightBottomLat) {
		this.rightBottomLat = rightBottomLat;
	}
	
	/**
	 * 
	 * @return 国家
	 */
	public Long getCountry(){
		return country;
	}
	
	/**
	 * 
	 * @param country 国家
	 */	
	public void setCountry (Long country) {
		this.country = country;
	}
	
	/**
	 * 
	 * @return 省
	 */
	public Long getProvince(){
		return province;
	}
	
	/**
	 * 
	 * @param province 省
	 */	
	public void setProvince (Long province) {
		this.province = province;
	}
	
	/**
	 * 
	 * @return 市
	 */
	public Long getCity(){
		return city;
	}
	
	/**
	 * 
	 * @param city 市
	 */	
	public void setCity (Long city) {
		this.city = city;
	}
	
	/**
	 * 
	 * @return 区县
	 */
	public Long getCounty(){
		return county;
	}
	
	/**
	 * 
	 * @param county 区县
	 */	
	public void setCounty (Long county) {
		this.county = county;
	}
	
	/**
	 * 
	 * @return 乡镇
	 */
	public Long getTown(){
		return town;
	}
	
	/**
	 * 
	 * @param town 乡镇
	 */	
	public void setTown (Long town) {
		this.town = town;
	}
	
	/**
	 * 
	 * @return 地区/商圈
	 */
	public Long getRegion(){
		return region;
	}
	
	/**
	 * 
	 * @param region 地区/商圈
	 */	
	public void setRegion (Long region) {
		this.region = region;
	}
	
	/**
	 * 
	 * @return 全地址
	 */
	public String getAddress(){
		return address;
	}
	
	/**
	 * 
	 * @param address 全地址
	 */	
	public void setAddress (String address) {
		this.address = address;
	}
	


}
