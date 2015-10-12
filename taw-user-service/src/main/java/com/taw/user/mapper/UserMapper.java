package com.taw.user.mapper;
import java.util.List;
import java.util.Map;
import com.taw.user.domain.User;

/**
 * t_um_user
 * 
 * 
 * @author Gen
 */
public interface UserMapper  {

	User loadUser(Integer id);
	
	List<User> loadUserDynamic(Map<String,Object> params);
	
	int countUserDynamic(Map<String,Object> params);
	
	void insertUser(User user);
	
	void deleteUser(Integer id);
	
	void deleteUserDynamic(Map<String,Object> params);
	
	void updateUser(User user);
	
	void updateUserDynamic(Map<String,Object> params);
	
	void updateUserWithoutNull(User user);


}