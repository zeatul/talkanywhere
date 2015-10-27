package com.taw.user.mapper;
import java.util.List;
import java.util.Map;
import com.taw.user.domain.PartnerDomain;
import org.apache.ibatis.annotations.Param;

/**
 * t_um_partner
 * 
 * 
 * @author Gen
 */
public interface PartnerMapper  {

	PartnerDomain loadPartner(@Param("id")Long id );
	
	List<PartnerDomain> loadPartnerDynamic(Map<String,Object> params);
	
	int count(Map<String,Object> params);
	
	int insert(PartnerDomain partnerDomain);
	
	int delete(@Param("id")Long id );
	
	int deleteDynamic(Map<String,Object> params);
	
	int updatePartner(PartnerDomain partnerDomain);
	
	int updatePartnerWithoutNull(PartnerDomain partnerDomain);
	
	int update(Map<String,Object> params);
	
	


}