package com.taw.scene.service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class NicknameService {
	
	private static ArrayList<String> adjList = new ArrayList<String>(1000);
	private static ArrayList<String> nounList =  new ArrayList<String>(1000);
	
	private static Random adjRandom = null;
	private static Random nounRandom = null;
	
	private static void fill(ArrayList<String> list ,String filename){
		BufferedReader bufferedReader = null;
		
		try {
			InputStream inputStream = new BufferedInputStream(NicknameService.class.getResourceAsStream(filename));
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"),1024*1024);
			while(bufferedReader.ready()){
				String line = bufferedReader.readLine();
				list.add(line);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			if (bufferedReader != null){
				try {
					bufferedReader.close();
				} catch (Exception e) {
					
				}
			}
		}
	}
	
	static{
		
		fill(adjList,"/adj.txt");
		adjRandom = new Random(adjList.size());
		fill(nounList,"/noun.txt");
		nounRandom = new Random(nounList.size());
	}
	
	
	public String genNickName(){
		long time = System.currentTimeMillis();
		time = time % adjList.size();
		adjRandom.nextInt((int)time);
		String nickname = adjList.get(adjRandom.nextInt(adjList.size()))+ "的" + nounList.get(nounRandom.nextInt(nounList.size()));		
		adjRandom.nextInt();
		return nickname;
	}
	
	public static void main(String[] args){
		NicknameService nicknameService = new NicknameService();
		for (int i=0;i<1000; i++){
			System.out.println(nicknameService.genNickName());
		}
	}

}
