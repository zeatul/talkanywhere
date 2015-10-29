package com.hawk.pub.sms.mapper;
import java.util.List;
import java.util.Map;
import com.hawk.pub.sms.domain.MessageDomain;
import org.apache.ibatis.annotations.Param;

/**
 * t_sm_message
 * 
 * 
 * @author Gen
 */
public interface MessageMapper  {

	MessageDomain loadMessage(@Param("id")Long id );
	
	List<MessageDomain> loadMessageDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(MessageDomain messageDomain);
	
	int delete(@Param("id")Long id );
	
	int deleteDynamic(Map<String,Object> params);
	
	int updateMessage(MessageDomain messageDomain);
	
	int updateMessageWithoutNull(MessageDomain messageDomain);
	
	int update(Map<String,Object> params);
	
	


}