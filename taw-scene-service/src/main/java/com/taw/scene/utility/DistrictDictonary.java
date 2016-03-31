package com.taw.scene.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistrictDictonary {
	
	public static Map<String,String> cityIdMap = new HashMap<String,String>();
	public static Map<String,String> provinceIdMap = new HashMap<String,String>();
	public static Map<String,String> countryIdMap = new HashMap<String,String>();
	public static Map<String,String> countyIdMap = new HashMap<String,String>();
	
	public static List<District> countryList = new ArrayList<District>();
	
	/**
	 * 国家 保留 主键 1-1000
	 * 省份,直辖市， 保留主键 1001 -10000
	 * 地级城市 保留主键 10001 -30000
	 * 区县级城市 保留主键 30001 -100000
	 * 乡镇级城市 保留主键 100001 -300000
	 */
	
	private static class District{
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public District getParent() {
			return parent;
		}
		public void setParent(District parent) {
			this.parent = parent;
		}
		public int getLevel() {
			return level;
		}
		public void setLevel(int level) {
			this.level = level;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		private String name;
		private int id;
		private District parent;
		private int level;
		private String path;

	}
	
	
	static{
		
		//country
		District district = new District();
		district.setName("中国");
		district.setLevel(0);
		district.setParent(null);
		district.setId(1);
		countryList.add(district);
		
		
		
		
		
		countyIdMap.put("乾县", "30001");
		cityIdMap.put("上海", "10001");
		cityIdMap.put("西安", "10002");
		cityIdMap.put("成都", "10003");
		cityIdMap.put("杭州", "10004");
		cityIdMap.put("乾县", "10005");
		provinceIdMap.put("上海", "1001");
		provinceIdMap.put("西安", "1002");
		provinceIdMap.put("成都", "1003");
		provinceIdMap.put("杭州", "1004");
		provinceIdMap.put("乾县", "1002");
		countryIdMap.put("上海", "1");
		countryIdMap.put("西安", "1");
		countryIdMap.put("成都", "1");	
		countryIdMap.put("杭州", "1");
		countryIdMap.put("乾县", "1");
	}

}
