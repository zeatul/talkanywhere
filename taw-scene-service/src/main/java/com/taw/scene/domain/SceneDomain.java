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
	
	/*经度*/
	private java.math.BigDecimal lng;
	
	/*纬度*/
	private java.math.BigDecimal lat;
	
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
	 * @return 经度
	 */
	public java.math.BigDecimal getLng(){
		return lng;
	}
	
	/**
	 * 
	 * @param lng 经度
	 */	
	public void setLng (java.math.BigDecimal lng) {
		this.lng = lng;
	}
	
	/**
	 * 
	 * @return 纬度
	 */
	public java.math.BigDecimal getLat(){
		return lat;
	}
	
	/**
	 * 
	 * @param lat 纬度
	 */	
	public void setLat (java.math.BigDecimal lat) {
		this.lat = lat;
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
