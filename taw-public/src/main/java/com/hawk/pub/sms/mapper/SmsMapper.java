package com.hawk.pub.sms.mapper;
import java.util.List;
import java.util.Map;
import com.hawk.pub.sms.domain.SmsDomain;
import org.apache.ibatis.annotations.Param;

/**
 * t_sm_sms
 * 
 * 
 * @author Gen
 */
public interface SmsMapper  {

	SmsDomain load(@Param("id")Long id );
	
	List<SmsDomain> loadDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(SmsDomain smsDomain);
	
	int delete(@Param("id")Long id );
	
	int deleteDynamic(Map<String,Object> params);
	
	int update(SmsDomain smsDomain);
	
	int updateWithoutNull(SmsDomain smsDomain);
	
	int update(Map<String,Object> params);
	
	


}