package com.taw.scene.utility;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.ibatis.mapping.Environment;

public class GenerateSenenInitSqlFromFile {

	public static void main(String[] args) throws Exception {
		File file = new File("c://场景数据2.csv");
		
		File outputFile = new File("C:\\mydata\\workspace\\talkanywhere\\taw-database\\sql\\update_temp2.sql");
		FileOutputStream fio = new FileOutputStream(outputFile);
		
		OutputStream os = new BufferedOutputStream(fio,1024*1024);
		
		//读
		
		FileInputStream fis =new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		BufferedReader in = new BufferedReader(new InputStreamReader(bis,"utf-8"), 1024*1024);
		int i=100000;
		while(in.ready()){
			String line = in.readLine();
			String[] strArray = line.split(",");
			
			StringBuilder sb = new StringBuilder();
			sb.append("insert into t_tm_scene(id,crdt,updt,status,name,kind,radius,center_lng,center_lat,left_top_lng,left_top_lat,left_bottom_lng,left_bottom_lat,right_top_lng,right_top_lat,right_bottom_lng,right_bottom_lat,country,province,city,county,address) values(");
			sb.append(i++).append(","); //id
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
			
			sb.append(System.lineSeparator());
			os.write(sb.toString().getBytes("utf-8"));
			
			if (i%30==0)
				os.flush();
			
		}
		
		in.close();
		bis.close();
		fis.close();
		
		
		
		//写
		String dir = System.getProperty("user.dir");
		
		
	
		
		os.flush();
		
		os.close();
		fio.close();
		
		
	}

}
