package com.taw.scene.mapper;
import java.util.List;
import java.util.Map;
import com.taw.scene.domain.MessageDomain;
import org.apache.ibatis.annotations.Param;

/**
 * t_tm_message
 * 
 * 
 * @author Gen
 */
public interface MessageMapper  {

	MessageDomain load(@Param("id")Long id );
	
	List<MessageDomain> loadDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(MessageDomain messageDomain);
	
	int delete(@Param("id")Long id );
	
	int deleteDynamic(Map<String,Object> params);
	
	int update(MessageDomain messageDomain);
	
	int updateWithoutNull(MessageDomain messageDomain);
	
	int update(Map<String,Object> params);
	
	


}