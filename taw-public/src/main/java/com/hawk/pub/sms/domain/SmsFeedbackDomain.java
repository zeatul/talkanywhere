package com.hawk.pub.sms.domain;
import java.io.Serializable;
import java.util.Date;



/**
 * t_sm_sms_feedback
 * 
 * 
 * @author Code-Gen
 */
public class SmsFeedbackDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/*主键*/
	private Long id;
	
	/*消息主键*/
	private Long messageId;
	
	/*发送时间*/
	private Date sendTime;
	
	/*失败反馈*/
	private String feedback;
	
	
	/**
	 * 
	 * @return 主键
	 */
	public Long getId(){
		return id;
	}
	
	/**
	 * 
	 * @param id 主键
	 */	
	public void setId (Long id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return 消息主键
	 */
	public Long getMessageId(){
		return messageId;
	}
	
	/**
	 * 
	 * @param messageId 消息主键
	 */	
	public void setMessageId (Long messageId) {
		this.messageId = messageId;
	}
	
	/**
	 * 
	 * @return 发送时间
	 */
	public Date getSendTime(){
		return sendTime;
	}
	
	/**
	 * 
	 * @param sendTime 发送时间
	 */	
	public void setSendTime (Date sendTime) {
		this.sendTime = sendTime;
	}
	
	/**
	 * 
	 * @return 失败反馈
	 */
	public String getFeedback(){
		return feedback;
	}
	
	/**
	 * 
	 * @param feedback 失败反馈
	 */	
	public void setFeedback (String feedback) {
		this.feedback = feedback;
	}
	


}
