package com.taw.scene.utility;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class GenerateSenenInitSqlFromFile {

	public static void main(String[] args) throws Exception {
		File file = new File("");
		
		//读
		
		FileInputStream fis =new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		BufferedReader in = new BufferedReader(new InputStreamReader(bis,"utf-8"), 1024*1024);
		
		while(in.ready()){
			String line = in.readLine();
		}
		
		in.close();
		bis.close();
		fis.close();
		
		String dir = System.getProperty("user.dir");
		
		//写
		
		File outputFile = new File("");
		FileOutputStream fio = new FileOutputStream(outputFile);
		
		OutputStream os = new BufferedOutputStream(fio,1024*1024);
		
		os.write("".getBytes("utf-8"));
		
		os.flush();
		
		os.close();
		fio.close();
		
		
	}

}
