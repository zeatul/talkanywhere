package com.hawk.pub.sms;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hawk.pub.exception.SendMessageFailedException;
import com.hawk.pub.pkgen.PkGenerator;
import com.hawk.pub.sms.domain.SmsDomain;
import com.hawk.pub.sms.mapper.SmsMapper;
import com.hawk.utility.DateTools;
import com.hawk.utility.DomainTools;
import com.hawk.utility.JsonTools;
import com.hawk.utility.check.CheckTools;
import com.hawk.utility.httpclient.HttpClientHelper;

@Service
public class SMSService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SmsMapper smsMapper;
	
	@Autowired
	@Qualifier("smsHttpClientHelper")
	private HttpClientHelper smsHttpClientHelper;
	
	@Autowired
	private SMSConfigure smsConfigure;
	
	public void SendMessage(SendMessageParam param) throws Exception{
		
		CheckTools.check(param);
		
		SmsDomain messageDomain = new SmsDomain();
		
		DomainTools.copy(param, messageDomain);

		Date now = DateTools.now();
		messageDomain.setCrdt(now);
		messageDomain.setUpdt(now);
		messageDomain.setExpireTime(DateTools.addMinutes(now, 30)); /*30分钟有效*/
		messageDomain.setId(PkGenerator.genPk());
		messageDomain.setMaxTimes(1);
		messageDomain.setSendTimes(0);
		messageDomain.setSendTime(null);
		messageDomain.setStatus(EnumMessageStatus.UNSENT.toString());
		String mobile = param.getMobile();
		messageDomain.setMobile(mobile);
		String authCode = param.getMessage();
		messageDomain.setTplId("8379");
		messageDomain.setMessage("【随意说】感谢您注册，您的验证码是#"+authCode+"#" );
		messageDomain.setParams(authCode);
		messageDomain.setKind(param.getKind());	
		
		/**
		 * 插入数据
		 */
		smsMapper.insert(messageDomain);
		
		try{
			
			messageDomain.setSendTime(DateTools.now()); 
			SendMessageResponse sendMessageResponse = send(authCode,mobile);			
			messageDomain.setSendTimes(1);
			
			messageDomain.setUpdt(DateTools.now());
			
			if (sendMessageResponse.isSuccess()){
				messageDomain.setStatus(EnumMessageStatus.SUCCESS.toString());
				messageDomain.setSid(sendMessageResponse.getSid());
			}else{
				messageDomain.setErrCode(sendMessageResponse.getErrCode());
				messageDomain.setErrMsg(sendMessageResponse.getErrMsg());
				messageDomain.setStatus(EnumMessageStatus.FAILED.toString());
				
				throw new SendMessageFailedException(sendMessageResponse.getErrMsg()+",errorCode="+sendMessageResponse.getErrCode());
			}
			
			
		}catch(Exception ex){
			if (ex instanceof SendMessageFailedException){
				throw ex;
			}
			messageDomain.setStatus(EnumMessageStatus.FAILED.toString());
			messageDomain.setErrMsg(ex.getClass().getSimpleName());
		}finally{
			smsMapper.update(messageDomain);
		}
		
		
		
		
	}
	
//	205401	错误的手机号码
//
//	205402	错误的短信模板ID
//
//	205403	网络错误,请重试
//
//	205404	发送失败，具体原因请参考返回reason
//
//	205405	号码异常/同一号码发送次数过于频繁
//
//	205406	不被支持的模板
//	
//	10001	错误的请求KEY	101
//
//	10002	该KEY无请求权限	102
//
//	10003	KEY过期	103
//
//	10004	错误的OPENID	104
//
//	10005	应用未审核超时，请提交认证	105
//
//	10007	未知的请求源	107
//
//	10008	被禁止的IP	108
//
//	10009	被禁止的KEY	109
//
//	10011	当前IP请求超过限制	111
//
//	10012	请求超过次数限制	112
//
//	10013	测试KEY超过请求限制	113
//
//	10014	系统内部异常，请重试	114
//
//	10020	接口维护	120
//
//	10021	接口停用
	
//	/****失败示例**/
//	{
//	    "reason": "错误的短信模板ID,请通过后台确认!!!",
//	    "result": [],
//	    "error_code": 205402
//	}
//
//	/****成功示例**/
//	{
//	    "reason": "短信发送成功",
//	    "result": {
//	        "count": 1, /*发送数量*/
//	        "fee": 1, /*扣除条数*/
//	        "sid": 2029865577 /*短信ID*/
//	    },
//	    "error_code": 0 /*发送成功*/
//	}
	
	private static class SendMessageResponse{
		public String getErrCode() {
			return errCode;
		}
		public void setErrCode(String errCode) {
			this.errCode = errCode;
		}
		public String getErrMsg() {
			return errMsg;
		}
		public void setErrMsg(String errMsg) {
			this.errMsg = errMsg;
		}
		public String getSid() {
			return sid;
		}
		public void setSid(String sid) {
			this.sid = sid;
		}
		public boolean isSuccess() {
			return success;
		}
		public void setSuccess(boolean success) {
			this.success = success;
		}
		public boolean isRetryable() {
			return retryable;
		}
		public void setRetryable(boolean retryable) {
			this.retryable = retryable;
		}
		private String errCode;
		private String errMsg;
		private String sid;
		private boolean success = false;
		private boolean retryable = false;
	}
	
	private SendMessageResponse send(String authCode,String mobile) throws Exception{
		
		if (smsConfigure.isSendSms()){
			//发送短信
			Map<String,String> params = new HashMap<String,String>();
			params.put("mobile", mobile);
			params.put("tpl_id", "9493");
			params.put("tpl_value", URLEncoder.encode("#code#=" , "utf-8") +authCode);
			params.put("tpl_value", "#code#="+authCode);
			params.put("key", "df69b06e12f3daefbe6e0cca4177e295");
			String result = smsHttpClientHelper.get("/sms/send", params);
			logger.info(result);
			Map<?,?> m =  JsonTools.toObject(result, HashMap.class);
			
			SendMessageResponse sendMessageResponse = new SendMessageResponse();
			
			String err_code = m.get("error_code").toString();
			sendMessageResponse.setErrCode(err_code);
			sendMessageResponse.setErrMsg((String)m.get("reason"));
			if (err_code.equals("0")){
				sendMessageResponse.setSuccess(true);
				Map<?,?> r = (Map<?,?>)m.get("result");
				sendMessageResponse.setSid(r.get("sid").toString());
			}
			return sendMessageResponse;
		}else{
			SendMessageResponse sendMessageResponse = new SendMessageResponse();
			sendMessageResponse.setSuccess(true);
			return sendMessageResponse; 
		}
		
		
		
	}
	

}
