package com.taw.scene.jms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.hawk.utility.JsonTools;

@Component
public class SceneEnterProducer {
	@Autowired
	@Qualifier("jmsTopicTemplate")
	private JmsTemplate jmsTemplate;
	
	@Autowired
	@Qualifier("sceneEnterDestination")
	private Destination destination;
	
	/**
	 * 发送会话通知消息
	 * @param message
	 */
	public void send(final Notification notification){
		jmsTemplate.send(destination, new MessageCreator(){

			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(JsonTools.toJsonString(notification));
			}
			
		});
	}
}
