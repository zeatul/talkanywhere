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

	UserContactDomain loadUserContact(@Param("id")Integer id );
	
	List<UserContactDomain> loadUserContactDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(UserContactDomain userContactDomain);
	
	int delete(@Param("id")Integer id );
	
	int deleteDynamic(Map<String,Object> params);
	
	int updateUserContact(UserContactDomain userContactDomain);
	
	int updateUserContactWithoutNull(UserContactDomain userContactDomain);
	
	int update(Map<String,Object> params);
	
	


}