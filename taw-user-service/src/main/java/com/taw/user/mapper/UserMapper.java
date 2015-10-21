package com.taw.user.mapper;
import java.util.List;
import java.util.Map;
import com.taw.user.domain.UserDomain;
import org.apache.ibatis.annotations.Param;

/**
 * t_um_user
 * 
 * 
 * @author Gen
 */
public interface UserMapper  {

	UserDomain loadUser(@Param("id")Integer id );
	
	List<UserDomain> loadUserDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(UserDomain userDomain);
	
	int delete(@Param("id")Integer id );
	
	int deleteDynamic(Map<String,Object> params);
	
	int updateUser(UserDomain userDomain);
	
	int updateUserWithoutNull(UserDomain userDomain);
	
	int update(Map<String,Object> params);
	
	


}