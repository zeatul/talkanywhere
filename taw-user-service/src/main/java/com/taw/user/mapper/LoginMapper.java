package com.taw.user.mapper;
import java.util.List;
import java.util.Map;
import com.taw.user.domain.Login;

/**
 * t_um_login
 * 
 * 
 * @author Gen
 */
public interface LoginMapper  {

	Login loadLogin(Integer id);
	
	List<Login> loadLoginDynamic(Map<String,Object> params);
	
	int countLoginDynamic(Map<String,Object> params);
	
	void insertLogin(Login login);
	
	void deleteLogin(Integer id);
	
	void deleteLoginDynamic(Map<String,Object> params);
	
	void updateLogin(Login login);
	
	void updateLoginDynamic(Map<String,Object> params);
	
	void updateLoginWithoutNull(Login login);


}