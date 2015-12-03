package com.taw.scene.mapper;
import java.util.List;
import java.util.Map;
import com.taw.scene.domain.FootPrintDetailDomain;
import org.apache.ibatis.annotations.Param;

/**
 * t_tm_foot_print_detail
 * 
 * 
 * @author Gen
 */
public interface FootPrintDetailMapper  {

	FootPrintDetailDomain load(@Param("id")Long id );
	
	List<FootPrintDetailDomain> loadDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(FootPrintDetailDomain footPrintDetailDomain);
	
	int delete(@Param("id")Long id );
	
	int deleteDynamic(Map<String,Object> params);
	
	int update(FootPrintDetailDomain footPrintDetailDomain);
	
	int updateWithoutNull(FootPrintDetailDomain footPrintDetailDomain);
	
	int update(Map<String,Object> params);
	
	


}