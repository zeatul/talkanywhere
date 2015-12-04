package com.taw.scene.mapperex;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * t_tm_message
 * 
 * 
 * @author Gen
 */
public interface MessageExMapper  {

	/**
	 * 删除指定接收者的指定id的数据
	 * @param ids
	 * @param receiverId
	 */
	void deleteByIds(@Param("ids")List<Long> ids ,@Param("receiverId")Long receiverId);


}