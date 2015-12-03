package com.hawk.pub.sms.mapper;
import java.util.List;
import java.util.Map;
import com.hawk.pub.sms.domain.SmsFeedbackDomain;
import org.apache.ibatis.annotations.Param;

/**
 * t_sm_sms_feedback
 * 
 * 
 * @author Gen
 */
public interface SmsFeedbackMapper  {

	SmsFeedbackDomain load(@Param("id")Long id );
	
	List<SmsFeedbackDomain> loadDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(SmsFeedbackDomain smsFeedbackDomain);
	
	int delete(@Param("id")Long id );
	
	int deleteDynamic(Map<String,Object> params);
	
	int update(SmsFeedbackDomain smsFeedbackDomain);
	
	int updateWithoutNull(SmsFeedbackDomain smsFeedbackDomain);
	
	int update(Map<String,Object> params);
	
	


}