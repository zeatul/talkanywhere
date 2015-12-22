package com.hawk.pub.sms;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.hawk.pub.sms.EnumMessageKind;
import com.hawk.pub.sms.SMSService;
import com.hawk.pub.sms.SendMessageParam;

@ContextConfiguration(locations={"classpath*:com/hawk/pub/spring/applicationContext-pub-*.xml"})
public class SMSServiceTest extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	private SMSService smsService;
	
	@Test
	public void testSendMessage() throws Exception{
		
		SendMessageParam param = new SendMessageParam();
		param.setMobile("13916082481");
		param.setMessage("123456");
		param.setKind(EnumMessageKind.AUTH_CODE.toString());
		
		smsService.SendMessage(param);
		
	}

}
