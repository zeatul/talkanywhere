package com.taw.scene.mapperex;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.taw.scene.domain.ConversationDomain;

public interface ConversationExMapper {
	
	public List<ConversationDomain> searchConversationInScene(Map<String,Object> params);
	
	/**
	 * 删除我发送的消息
	 * @param ids 消息主键
	 * @param postUserId 发送者id
	 */
	void deleteByIds(@Param("ids")List<Long> ids ,@Param("postUserId")Long postUserId);

}
