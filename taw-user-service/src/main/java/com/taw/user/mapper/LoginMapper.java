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

	LoginDomain load(@Param("token")String token );
	
	List<LoginDomain> loadDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(LoginDomain loginDomain);
	
	int delete(@Param("token")String token );
	
	int deleteDynamic(Map<String,Object> params);
	
	int update(LoginDomain loginDomain);
	
	int updateWithoutNull(LoginDomain loginDomain);
	
	int update(Map<String,Object> params);
	
	


}