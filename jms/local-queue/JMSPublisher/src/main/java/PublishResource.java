import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("publish")
public class PublishResource {

  @Inject JMSContext context;
  
  @Resource(lookup = "java:/jms/queue/myqueue")
  private Queue queue;
  
  
  @POST
  public Response publish() {
    
    context.createProducer().send(queue, "Test");
    
    return Response.ok().build();
  }
}
