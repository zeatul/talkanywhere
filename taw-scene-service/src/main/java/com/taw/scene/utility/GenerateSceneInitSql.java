package com.taw.scene.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateSceneInitSql {
	
	private static List<String> sceneList = new ArrayList<String>();
	
	
	static{
		
//		城市,点场景,名称,西南-经度,西南-纬度,东北-经度,东北-纬度,,
		sceneList.add("上海,是,象屿都城中庭,121.3401550,31.1309730,,,,");
		sceneList.add("上海,否,象屿都城,121.3392390,31.1300760,121.3420960,31.1323330,,");
		sceneList.add("上海,否,九亭大街街区,121.3325560,31.1266910,121.3452940,31.1403230,,");
		sceneList.add("上海,是,好又多超市虹泾路店,121.3450060,31.1282370,,,,");
		sceneList.add("上海,否,颐景园,121.3459000,31.1273560,121.3480020,31.1305090,,");
		sceneList.add("上海,否,复地艺墅南区,121.3505890,31.1274720,121.3532750,31.1291640,,");
		sceneList.add("上海,否,上海久鼎沙发厂,121.3554040,31.1295350,121.3565270,31.1312040,,");
		sceneList.add("上海,否,金湾小区,121.3584400,31.1251530,121.3596170,31.1277650,,");
		sceneList.add("上海,是,苹果园连锁大卖场,121.3671270,31.1241870,,,,");
		sceneList.add("上海,是,莘庄地铁站,121.3916420,31.1166520,,,,");
		sceneList.add("上海,是,九亭地铁站,121.3254100,31.1438620,,,,");
		sceneList.add("上海,否,九久青年城,121.3229300,31.1422780,121.3283560,31.1448510,,");
		sceneList.add("上海,否,亭汇花苑,121.3201540,31.1424940,121.3225440,31.1471380,,");
		sceneList.add("上海,是,上海健帆车辆部件公司,121.3264430,31.1457470,,,,");
		sceneList.add("上海,否,正好广场,121.3306740,31.1426950,121.3312760,31.1434290,,");
		sceneList.add("上海,是,红膏生态大闸蟹,121.3321560,31.1388850,,,,");
		sceneList.add("上海,是,新上海城市广场,121.493995,31.2353790,,,,");
		sceneList.add("上海,是,豫园地铁站,121.4939050,31.2340280,,,,");
		sceneList.add("上海,否,福佑门商场,121.4942910,31.2337040,121.4952430,31.2342900,,");
		sceneList.add("上海,是,星腾大厦,121.4936530,31.2362510,,,,");
		sceneList.add("上海,是,范仕达生活广场,121.4937250,31.2357720,,,,");
		sceneList.add("上海,否,豫园,121.4980550,31.2318740,121.4992230,31.2334800,,");
		sceneList.add("上海,是,三穗堂,121.4983960,31.2328160,,,,");
		sceneList.add("上海,是,大假山,121.4984680,31.2330940,,,,");
		sceneList.add("上海,是,得月楼,121.4988900,31.2326230,,,,");
		sceneList.add("上海,是,藏书楼,121.4990340,31.2322750,,,,");
		sceneList.add("上海,是,上海环球金融中心,121.5143500,31.2407970,,,,");
		sceneList.add("上海,是,金茂大厦,121.5124280,31.2408510,,,,");
		sceneList.add("上海,否,陆家嘴中心绿地,121.5107210,31.2425880,121.5135060,31.2456210,,");
		sceneList.add("上海,是,东方明珠,121.5063100,31.2453980,,,,");
		sceneList.add("西安,是,伟丰花园12栋,108.9472220,34.2049600,,,,");
		sceneList.add("西安,否,伟丰花园,108.9459470,34.2046020,108.9474690,34.2063450,,");
		sceneList.add("西安,是,西安市机动车排气污染监控监督中心,108.9475370,34.2045380,,,,");
		sceneList.add("西安,否,西安工业科技学院,108.8886570,34.2178490,108.8944780,34.2221770,,");
		sceneList.add("西安,否,西安工程技术学院,108.9431080,34.2046390,108.9446940,34.2060240,,");
		sceneList.add("西安,否,朱雀大街丈八东路口,108.9448100,34.2038670,108.9460370,34.2047100,,");
		sceneList.add("西安,是,朱雀南路工商所,108.9461040,34.2046130,,,,");
		sceneList.add("西安,是,西安永嘉汽车修理航,108.9451700,34.2058440,,,,");
		sceneList.add("西安,否,西安市第八医院家属院,108.9498950,34.2025830,108.9507210,34.2041090,,");
		sceneList.add("西安,是,陕西自然博物馆,108.9529530,34.2022920,,,,");
		sceneList.add("西安,是,陕西电视台,108.9535600,34.2003210,,,,");
		sceneList.add("西安,否,曲江国际会展中心,108.9546290,34.2000970,108.9596410,34.2041350,,");
		sceneList.add("西安,是,西安万融信息科技,108.9570000,34.2014260,,,,");
		sceneList.add("西安,否,陕西师范大学雁塔校区,108.9532180,34.2082550,108.9625970,34.2116590,,");
		sceneList.add("西安,否,大唐芙蓉园,108.9760350,34.2149420,108.9840480,34.2228230,,");
		sceneList.add("西安,是,大唐芙蓉园-大唐疆域图,108.9802930,34.2182520,,,,");
		sceneList.add("西安,是,大唐芙蓉园-天王俑,108.9800960,34.2186440,,,,");
		sceneList.add("西安,是,大唐芙蓉园-厕所,108.9798170,34.2182520,,,,");
		sceneList.add("西安,是,大唐芙蓉园-走狮,108.9801540,34.2178900,,,,");
		sceneList.add("西安,是,大唐芙蓉园-博山炉,108.9802710,34.2181250,,,,");
		sceneList.add("西安,是,曲江立交桥,109.0108180,34.2021350,,,,");
		sceneList.add("成都,是,鸿生花园,104.0267320,30.6877730,,,,");
		sceneList.add("成都,是,天鹅星座,104.0279180,30.6891390,,,,");
		sceneList.add("成都,否,金沙遗址,104.0157730,30.6854440,104.0215940,30.6903500,,");
		sceneList.add("杭州,是,万福中心,120.2167590,30.1883550,,,,");
		sceneList.add("杭州,是,卡斯比亚花店,120.2178010,30.1891980,,,,");
		sceneList.add("杭州,否,滨康小区,120.2138850,30.1852020,120.2172980,30.1874190,,");
		sceneList.add("杭州,否,滨康路江晖路口,120.2162200,30.1867160,120.2185560,30.1886830,,");
		sceneList.add("杭州,是,春风集团大厦,120.2212330,30.1901340,,,,");
		sceneList.add("杭州,是,西兴地铁站,120.2271080,30.1933180,,,,");
		sceneList.add("杭州,否,杭州市旅游职业学校,120.2137230,30.1961120,120.2169210,30.1980160,,");
		sceneList.add("杭州,否,春波南苑,120.2200110,30.1933180,120.2241970,30.1963620,,");
		sceneList.add("杭州,否,滨江区体育馆,120.2093480,30.2011920,120.2110550,30.2026670,,");
		sceneList.add("杭州,是,滨江区政府,120.2182410,30.2147460,,,,");
		sceneList.add("杭州,否,滨江公园,120.2002390,30.2133100,120.2219060,30.2273220,,");


	}
	
	private static void genSql(){
		for (int i=0; i<sceneList.size(); i++){
			String str = sceneList.get(i);
			String[] strArray = str.split(",");
			StringBuilder sb = new StringBuilder();
			sb.append("insert into t_tm_scene(id,crdt,updt,status,name,kind,radius,center_lng,center_lat,left_top_lng,left_top_lat,left_bottom_lng,left_bottom_lat,right_top_lng,right_top_lat,right_bottom_lng,right_bottom_lat,country,province,city,county,address) values(");
			sb.append(i+1).append(","); //id
			sb.append("current_timestamp").append(","); //crdt
			sb.append("current_timestamp").append(","); //updt
			sb.append("'1'").append(","); //status
			sb.append("'").append(strArray[2]).append("'").append(","); //name
			sb.append("'1'").append(","); //kind
			
			String radius = "0";
			String leftBottomLng = strArray[3];
			String leftBottomLat = strArray[4];
			
			String rightTopLng = leftBottomLng;
			String rightTopLat = leftBottomLat;
			
			String leftTopLng = leftBottomLng;
			String leftTopLat = leftBottomLat;
			
			String rightBottomLng = leftBottomLng;
			String rightBottomLat = leftBottomLat;
			
			String centerLng =  leftBottomLng;
			String centerLat = leftBottomLat;
			if (strArray[1].equals("是")){
				radius = "0";	
			}else{
				radius = "1";
				rightTopLng = strArray[4];
				rightTopLat = strArray[5];
				
				leftTopLng = leftBottomLng;
				leftTopLat = rightTopLat;
				
				rightBottomLng = rightTopLng;
				rightBottomLat = leftBottomLat;
				
				centerLng = new Double((Double.parseDouble(leftBottomLng) + Double.parseDouble(rightTopLng))/2).toString();
				centerLat = new Double((Double.parseDouble(leftBottomLat) + Double.parseDouble(rightTopLat))/2).toString();
				
				
			}
			
			sb.append(radius).append(",");  //radius
			sb.append(centerLng).append(",");  //centerLng
			sb.append(centerLat).append(",");  //centerLat
			
			sb.append(leftTopLng).append(",");  //leftTopLng
			sb.append(leftTopLat).append(",");  //leftTopLng
			
			sb.append(leftBottomLng).append(",");  //leftBottomLng
			sb.append(leftBottomLat).append(",");  //leftBottomLng
			
			sb.append(rightTopLng).append(",");  //rightTopLng
			sb.append(rightTopLat).append(",");  //rightTopLng
			
			sb.append(rightBottomLng).append(",");  //rightBottomLng
			sb.append(rightBottomLat).append(",");  //rightBottomLng
				
			sb.append(DistrictDictonary.countryIdMap.get(strArray[0])).append(","); //country
			sb.append(DistrictDictonary.provinceIdMap.get(strArray[0])).append(","); //province
			sb.append(DistrictDictonary.cityIdMap.get(strArray[0])).append(","); //city
			sb.append(DistrictDictonary.countyIdMap.get(strArray[0])).append(","); //county										
			sb.append("'").append(strArray[0]).append("'").append(");"); //address
			System.out.println(sb.toString());
		}
	}
	
	public static void main(String[] args){
		genSql();
	}
	
	
}
