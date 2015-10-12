package com.taw.user.mapper;
import java.util.List;
import java.util.Map;
import com.taw.user.domain.UserContact;

/**
 * t_um_user_contact
 * 
 * 
 * @author Gen
 */
public interface UserContactMapper  {

	UserContact loadUserContact(Integer id);
	
	List<UserContact> loadUserContactDynamic(Map<String,Object> params);
	
	int countUserContactDynamic(Map<String,Object> params);
	
	void insertUserContact(UserContact userContact);
	
	void deleteUserContact(Integer id);
	
	void deleteUserContactDynamic(Map<String,Object> params);
	
	void updateUserContact(UserContact userContact);
	
	void updateUserContactDynamic(Map<String,Object> params);
	
	void updateUserContactWithoutNull(UserContact userContact);


}