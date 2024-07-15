package org.wildfly.examples.mdb;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
//import org.jboss.ejb3.annotation.ResourceAdapter;

import java.util.logging.Logger;

@MessageDriven(
		name = "TestQueueMDB",
		activationConfig = {
				@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue"),
				@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/remote/testQueue")
		}
)
//@ResourceAdapter("remote-activemq-pcf") // name of the pooled-connection-factory resource
public class TestQueueMDB implements MessageListener {

	private static final Logger LOGGER = Logger.getLogger(TestQueueMDB.class.toString());

	public void onMessage(Message rcvMessage) {
		TextMessage msg = null;
		try {
			if (rcvMessage instanceof TextMessage) {
				msg = (TextMessage) rcvMessage;
				LOGGER.info("Received Message from queue: " + msg.getText());
			} else {
				LOGGER.warning("Message of wrong type: " + rcvMessage.getClass().getName());
			}
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}
}
