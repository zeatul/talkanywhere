package com.hawk.pub.sms;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.pub.pkgen.PkGenerator;
import com.hawk.pub.sms.domain.SmsDomain;
import com.hawk.pub.sms.mapper.SmsMapper;
import com.hawk.utility.DateTools;
import com.hawk.utility.DomainTools;
import com.hawk.utility.check.CheckTools;

@Service
public class SMSService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SmsMapper smsMapper;
	
	public void SendMessage(SendMessageParam param) throws Exception{
		
		CheckTools.check(param);
		
		SmsDomain messageDomain = new SmsDomain();
		
		DomainTools.copy(param, messageDomain);

		Date now = DateTools.now();
		messageDomain.setCrdt(now);
		messageDomain.setUpdt(now);
		messageDomain.setExpireTime(DateTools.addMinutes(now, 30)); /*30分钟有效*/
		messageDomain.setId(PkGenerator.genPk());
		messageDomain.setMaxTimes(3);
		messageDomain.setSendTimes(0);
		messageDomain.setSendTime(null);
		messageDomain.setStatus(EnumMessageStatus.UNSENT.toString());
		
		smsMapper.insert(messageDomain);
		
		logger.info("Success to persist sms message!");
		
	}
	

}
