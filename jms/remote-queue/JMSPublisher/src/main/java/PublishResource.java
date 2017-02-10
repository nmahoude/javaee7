import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("publish")
public class PublishResource {
  @Inject
//  @JMSConnectionFactory(value="java:/global/jms/remotequeue")
  @JMSConnectionFactory(value="java:/global/jms/remotequeueSND")
  JMSContext context;
  
  @POST
  public Response publish() {
    Queue queue = context.createQueue("queue");
    context.createProducer().send(queue, "Test");
    
    return Response.ok().build();
  }
}
