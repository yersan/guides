package org.wildfly.examples;

import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.jms.JMSConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.Queue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/message")
public class TestQueueEndpoint {

    @Resource(lookup="java:/jms/remote/testQueue")
    Queue testQueue;

    @Inject
    @JMSConnectionFactory("java:/jms/remote/activemqPCF")
    private JMSContext context;

    @GET
    @Path("/send")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sendMessage(final @QueryParam("content") String content) throws JMSException {
        String response = "Sent " + content + " to " + testQueue.getQueueName();
        context.createProducer().send(testQueue, content);
        return Response.ok(response).build();
    }
}
