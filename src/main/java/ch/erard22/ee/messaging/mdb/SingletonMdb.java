package ch.erard22.ee.messaging.mdb;

import org.jboss.ejb3.annotation.Pool;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import static org.slf4j.LoggerFactory.getLogger;

@Pool("single-instance-pool")
@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/queue/ExpiryQueue")
        },
        mappedName = "jms/queue/ExpiryQueue")
public class SingletonMdb implements MessageListener {

    private static final Logger logger = getLogger(SingletonMdb.class);

    @Override
    public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;

        try {
            String text = tm.getText();
            logger.info("Message received: {}", text);
        } catch (JMSException e) {
            logger.error("Error occured: ", e);
        }

    }


    @PostConstruct
    public void postConstruct() {
        logger.info("instance created {}", this.toString());
    }
}
