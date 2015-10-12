package com.taw.user.mapper;
import java.util.List;
import java.util.Map;
import com.taw.user.domain.Partner;

/**
 * t_um_partner
 * 
 * 
 * @author Gen
 */
public interface PartnerMapper  {

	Partner loadPartner(Integer id);
	
	List<Partner> loadPartnerDynamic(Map<String,Object> params);
	
	int countPartnerDynamic(Map<String,Object> params);
	
	void insertPartner(Partner partner);
	
	void deletePartner(Integer id);
	
	void deletePartnerDynamic(Map<String,Object> params);
	
	void updatePartner(Partner partner);
	
	void updatePartnerDynamic(Map<String,Object> params);
	
	void updatePartnerWithoutNull(Partner partner);


}