package com.taw.scene.mapper;
import java.util.List;
import java.util.Map;
import com.taw.scene.domain.FootPrintDomain;
import org.apache.ibatis.annotations.Param;

/**
 * t_tm_foot_print
 * 
 * 
 * @author Gen
 */
public interface FootPrintMapper  {

	FootPrintDomain loadFootPrint(@Param("id")Long id );
	
	List<FootPrintDomain> loadFootPrintDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(FootPrintDomain footPrintDomain);
	
	int delete(@Param("id")Long id );
	
	int deleteDynamic(Map<String,Object> params);
	
	int updateFootPrint(FootPrintDomain footPrintDomain);
	
	int updateFootPrintWithoutNull(FootPrintDomain footPrintDomain);
	
	int update(Map<String,Object> params);
	
	


}