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

	UserDomain load(@Param("id")Long id );
	
	List<UserDomain> loadDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(UserDomain userDomain);
	
	int delete(@Param("id")Long id );
	
	int deleteDynamic(Map<String,Object> params);
	
	int update(UserDomain userDomain);
	
	int updateWithoutNull(UserDomain userDomain);
	
	int update(Map<String,Object> params);
	
	


}