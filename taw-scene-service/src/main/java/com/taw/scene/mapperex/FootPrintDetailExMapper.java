package com.taw.scene.mapperex;
import java.util.List;
import com.taw.scene.domain.FootPrintDetailDomain;

import org.apache.ibatis.annotations.Param;

/**
 * t_tm_foot_print_detail
 * 
 * 
 * @author Gen
 */
public interface FootPrintDetailExMapper  {

//	/**
//	 * 查询没有离开的指定场景进入明细
//	 * @return FootPrintDetailDomain List
//	 */
//	List<FootPrintDetailDomain> queryUnLeavedFootPrintDetailDomains(@Param("token")String token,@Param("sceneId")Long sceneId,@Param("userId")Long userId);
	
	/**
	 * 查询没有离开的指定场景进入明细
	 * @return FootPrintDetailDomain List
	 */
	List<FootPrintDetailDomain> queryUnLeavedFootPrintDetailDomains2(@Param("sceneId")Long sceneId,@Param("userId")Long userId);
	
	/**
	 * 查询没有离开的指定场景进入明细
	 * @return FootPrintDetailDomain List
	 */
	List<FootPrintDetailDomain> queryUnLeavedFootPrintDetailDomains3(@Param("sceneId")Long sceneId,@Param("offset")Integer offset,@Param("limit")Integer limit);
	
	
	/**
	 * 查询没有离开的所有场景id
	 * @param token
	 * @return
	 */
	List<Long> queryUnLeavedSceneId(@Param("token")String token);


}