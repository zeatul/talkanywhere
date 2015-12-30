package com.taw.scene.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateSceneInitSql {
	
	private static List<String> sceneList = new ArrayList<String>();
	private static Map<String,String> cityIdMap = new HashMap<String,String>();
	private static Map<String,String> provinceIdMap = new HashMap<String,String>();
	private static Map<String,String> countryIdMap = new HashMap<String,String>();
	
	static{
		cityIdMap.put("上海", "10001");
		cityIdMap.put("西安", "10002");
		cityIdMap.put("成都", "10003");
		provinceIdMap.put("上海", "1001");
		provinceIdMap.put("西安", "1002");
		provinceIdMap.put("成都", "1003");
		countryIdMap.put("上海", "1");
		countryIdMap.put("西安", "1");
		countryIdMap.put("成都", "1");
		sceneList.add("上海,是,象屿都城中庭,121.3401550,31.1309730,0.0,");
		sceneList.add("上海,否,象屿都城,121.3392390,31.1300760,1,");
		sceneList.add("上海,否,九亭大街街区,121.3325560,31.1266910,1.5,");
		sceneList.add("上海,是,好又多超市虹泾路店,121.3450060,31.1282370,0,");
		sceneList.add("上海,否,颐景园,121.3459000,31.1273560,0.5,");
		sceneList.add("上海,否,复地艺墅南区,121.3505890,31.1274720,0.5,");
		sceneList.add("上海,否,上海久鼎沙发厂,121.3554040,31.1295350,0.5,");
		sceneList.add("上海,否,金湾小区,121.3584400,31.1251530,1,");
		sceneList.add("上海,是,苹果园连锁大卖场,121.3671270,31.1241870,0,");
		sceneList.add("上海,是,莘庄地铁站,121.3916420,31.1166520,0,");
		sceneList.add("上海,是,九亭地铁站,121.3254100,31.1438620,0,");
		sceneList.add("上海,否,九久青年城,121.3229300,31.1422780,1,");
		sceneList.add("上海,否,亭汇花苑,121.3201540,31.1424940,1,");
		sceneList.add("上海,是,上海健帆车辆部件公司,121.3264430,31.1457470,0,");
		sceneList.add("上海,否,正好广场,121.3306740,31.1426950,1.5,");
		sceneList.add("上海,是,红膏生态大闸蟹,121.3321560,31.1388850,0,");
		sceneList.add("上海,是,新上海城市广场,121.493995,31.2353790,0,");
		sceneList.add("上海,是,豫园地铁站,121.4939050,31.2340280,0,");
		sceneList.add("上海,否,福佑门商场,121.4942910,31.2337040,1,");
		sceneList.add("上海,是,星腾大厦,121.4936530,31.2362510,0,");
		sceneList.add("上海,是,范仕达生活广场,121.4937250,31.2357720,0,");
		sceneList.add("上海,否,豫园,121.4980550,31.2318740,1,");
		sceneList.add("上海,是,三穗堂,121.4983960,31.2328160,0,");
		sceneList.add("上海,是,大假山,121.4984680,31.2330940,0,");
		sceneList.add("上海,是,得月楼,121.4988900,31.2326230,0,");
		sceneList.add("上海,是,藏书楼,121.4990340,31.2322750,0,");
		sceneList.add("上海,是,上海环球金融中心,121.5143500,31.2407970,0,");
		sceneList.add("上海,是,金茂大厦,121.5124280,31.2408510,0,");
		sceneList.add("上海,否,陆家嘴中心绿地,121.5107210,31.2425880,1.5,");
		sceneList.add("上海,是,东方明珠,121.5063100,31.2453980,0,");
		sceneList.add("西安,是,伟丰花园12栋,108.9472220,34.2049600,0,");
		sceneList.add("西安,否,伟丰花园,108.9459470,34.2046020,0.5,");
		sceneList.add("西安,是,西安市机动车排气污染监控监督中心,108.9475370,34.2045380,0,");
		sceneList.add("西安,否,西安工业科技学院,108.8886570,34.2178490,1,");
		sceneList.add("西安,否,西安工程技术学院,108.9431080,34.2046390,1,");
		sceneList.add("西安,否,朱雀大街丈八东路口,108.9448100,34.2038670,1,");
		sceneList.add("西安,是,朱雀南路工商所,108.9461040,34.2046130,0,");
		sceneList.add("西安,是,西安永嘉汽车修理航,108.9451700,34.2058440,0,");
		sceneList.add("西安,否,西安市第八医院家属院,108.9498950,34.2025830,2,");
		sceneList.add("西安,是,陕西自然博物馆,108.9529530,34.2022920,0,");
		sceneList.add("西安,是,陕西电视台,108.9535600,34.2003210,0,");
		sceneList.add("西安,否,曲江国际会展中心,108.9546290,34.2000970,0.5,");
		sceneList.add("西安,是,西安万融信息科技,108.9570000,34.2014260,0,");
		sceneList.add("西安,否,陕西师范大学雁塔校区,108.9532180,34.2082550,0.5,");
		sceneList.add("西安,否,大唐芙蓉园,108.9760350,34.2149420,0.5,");
		sceneList.add("西安,是,大唐芙蓉园-大唐疆域图,108.9802930,34.2182520,0,");
		sceneList.add("西安,是,大唐芙蓉园-天王俑,108.9800960,34.2186440,0,");
		sceneList.add("西安,是,大唐芙蓉园-厕所,108.9798170,34.2182520,0,");
		sceneList.add("西安,是,大唐芙蓉园-走狮,108.9801540,34.2178900,0,");
		sceneList.add("西安,是,大唐芙蓉园-博山炉,108.9802710,34.2181250,0,");
		sceneList.add("西安,是,曲江立交桥,109.0108180,34.2021350,0,");
		sceneList.add("成都,是,鸿生花园,104.0267320,30.6877730,0,");
		sceneList.add("成都,是,天鹅星座,104.0279180,30.6891390,0,");
		sceneList.add("成都,否,金沙遗址,104.0157730,30.6854440,1,");

	}
	
	private static void genSql(){
		for (int i=0; i<sceneList.size(); i++){
			String str = sceneList.get(i);
			String[] strArray = str.split(",");
			StringBuilder sb = new StringBuilder();
			sb.append("insert into t_tm_scene(id,name,kind,lng,lat,radius,country,province,city,address) values(");
			sb.append(i+1).append(",");
			sb.append("'").append(strArray[2]).append("'").append(",");
			sb.append("'1'").append(",");
			sb.append(strArray[3]).append(",");
			sb.append(strArray[4]).append(",");
			sb.append(strArray[5]).append(",");
			sb.append(countryIdMap.get(strArray[0])).append(",");
			sb.append(provinceIdMap.get(strArray[0])).append(",");
			sb.append(cityIdMap.get(strArray[0])).append(",");
			sb.append("'").append(strArray[0]).append("'").append(");");
			System.out.println(sb.toString());
		}
	}
	
	public static void main(String[] args){
		genSql();
	}
	
	
}
