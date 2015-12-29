package com.taw.scene.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hawk.utility.JsonTools;
import com.taw.scene.netty.CtxHelper;

@Component
public class SceneLeaveConsumer implements MessageListener {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void onMessage(Message message) {

		try {
			if (!(message instanceof TextMessage))
				return;

			TextMessage tm = (TextMessage) message;
			String text = tm.getText();
			System.out.println("receive conversation topic message = " + text);
			
			Notification notification = JsonTools.toObject(text, Notification.class);
			
			CtxHelper.notifySceneLeave(notification);
		} catch (Exception ex) {
			logger.error("Meet error while dealing SceneConversationConsumer Jms Message", ex);
		}

	}

}
