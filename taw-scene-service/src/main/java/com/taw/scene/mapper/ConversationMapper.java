package com.taw.scene.mapper;
import java.util.List;
import java.util.Map;
import com.taw.scene.domain.ConversationDomain;
import org.apache.ibatis.annotations.Param;

/**
 * t_tm_conversation
 * 
 * 
 * @author Gen
 */
public interface ConversationMapper  {

	ConversationDomain loadConversation(@Param("id")Long id );
	
	List<ConversationDomain> loadConversationDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(ConversationDomain conversationDomain);
	
	int delete(@Param("id")Long id );
	
	int deleteDynamic(Map<String,Object> params);
	
	int updateConversation(ConversationDomain conversationDomain);
	
	int updateConversationWithoutNull(ConversationDomain conversationDomain);
	
	int update(Map<String,Object> params);
	
	


}