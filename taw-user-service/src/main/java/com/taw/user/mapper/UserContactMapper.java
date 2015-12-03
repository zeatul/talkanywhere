package com.taw.user.mapper;
import java.util.List;
import java.util.Map;
import com.taw.user.domain.UserContactDomain;
import org.apache.ibatis.annotations.Param;

/**
 * t_um_user_contact
 * 
 * 
 * @author Gen
 */
public interface UserContactMapper  {

	UserContactDomain load(@Param("id")Long id );
	
	List<UserContactDomain> loadDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(UserContactDomain userContactDomain);
	
	int delete(@Param("id")Long id );
	
	int deleteDynamic(Map<String,Object> params);
	
	int update(UserContactDomain userContactDomain);
	
	int updateWithoutNull(UserContactDomain userContactDomain);
	
	int update(Map<String,Object> params);
	
	


}