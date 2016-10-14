package com.taw.user.mapper;
import java.util.List;
import java.util.Map;
import com.taw.user.domain.VersionDomain;
import org.apache.ibatis.annotations.Param;

/**
 * t_um_version
 * 
 * 
 * @author Gen
 */
public interface VersionMapper  {

	VersionDomain load(@Param("id")Long id );
	
	List<VersionDomain> loadDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(VersionDomain versionDomain);
	
	int delete(@Param("id")Long id );
	
	int deleteDynamic(Map<String,Object> params);
	
	int update(VersionDomain versionDomain);
	
	int updateWithoutNull(VersionDomain versionDomain);
	
	int update(Map<String,Object> params);
	
	


}