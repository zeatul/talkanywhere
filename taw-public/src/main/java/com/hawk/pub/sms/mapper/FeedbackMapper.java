package com.hawk.pub.sms.mapper;
import java.util.List;
import java.util.Map;
import com.hawk.pub.sms.domain.FeedbackDomain;
import org.apache.ibatis.annotations.Param;

/**
 * t_sm_feedback
 * 
 * 
 * @author Gen
 */
public interface FeedbackMapper  {

	FeedbackDomain loadFeedback(@Param("id")Long id );
	
	List<FeedbackDomain> loadFeedbackDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(FeedbackDomain feedbackDomain);
	
	int delete(@Param("id")Long id );
	
	int deleteDynamic(Map<String,Object> params);
	
	int updateFeedback(FeedbackDomain feedbackDomain);
	
	int updateFeedbackWithoutNull(FeedbackDomain feedbackDomain);
	
	int update(Map<String,Object> params);
	
	


}