package com.taw.user.mapper;
import java.util.List;
import java.util.Map;
import com.taw.user.domain.LoginDomain;
import org.apache.ibatis.annotations.Param;

/**
 * t_um_login
 * 
 * 
 * @author Gen
 */
public interface LoginMapper  {

	LoginDomain loadLogin(@Param("token")String token );
	
	List<LoginDomain> loadLoginDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(LoginDomain loginDomain);
	
	int delete(@Param("token")String token );
	
	int deleteDynamic(Map<String,Object> params);
	
	int updateLogin(LoginDomain loginDomain);
	
	int updateLoginWithoutNull(LoginDomain loginDomain);
	
	int update(Map<String,Object> params);
	
	


}