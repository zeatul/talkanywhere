package com.taw.scene.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class NicknameService {
	
	
	public String genNickName(){
		return UUID.randomUUID().toString();
	}

}
