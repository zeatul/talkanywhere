package com.hawk.pub.sms;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hawk.pub.pkgen.PkGenerator;
import com.hawk.pub.sms.domain.MessageDomain;
import com.hawk.pub.sms.mapper.MessageMapper;
import com.hawk.utility.DateTools;
import com.hawk.utility.DomainTools;
import com.hawk.utility.check.CheckTools;

public class SMSService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MessageMapper messageMapper;
	
	public void SendMessage(SendMessageParam param) throws Exception{
		
		CheckTools.check(param);
		
		MessageDomain messageDomain = new MessageDomain();
		
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
		
		messageMapper.insert(messageDomain);
		
		logger.info("Success to send msg");
		
	}
	

}
