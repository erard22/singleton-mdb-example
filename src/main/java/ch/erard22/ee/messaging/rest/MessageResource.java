package ch.erard22.ee.messaging.rest;

import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.ws.rs.*;

import static org.slf4j.LoggerFactory.getLogger;

@Stateless
@Path("/message")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class MessageResource {

    private static final Logger logger = getLogger(MessageResource.class);

    @Inject
    private JMSContext context;

    @Resource(lookup = "java:/jms/queue/ExpiryQueue" +
            "")
    private Queue queue;


    @GET
    @Path("/{text}")
    public String sendMessage(@PathParam("text") String text) {
        context.createProducer().send(queue, text);
        return "sent: " + text;
    }

}
