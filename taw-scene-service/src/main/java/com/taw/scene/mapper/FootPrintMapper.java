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

	FootPrintDomain load(@Param("id")Long id );
	
	List<FootPrintDomain> loadDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(FootPrintDomain footPrintDomain);
	
	int delete(@Param("id")Long id );
	
	int deleteDynamic(Map<String,Object> params);
	
	int update(FootPrintDomain footPrintDomain);
	
	int updateWithoutNull(FootPrintDomain footPrintDomain);
	
	int update(Map<String,Object> params);
	
	


}