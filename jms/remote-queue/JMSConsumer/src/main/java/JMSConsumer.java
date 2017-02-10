import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(name = "RemoteMDB", 
  activationConfig = { 
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "queue"),
    @ActivationConfigProperty(propertyName = "connectionFactoryLookup", propertyValue = "java:/global/jms/remotequeueRCV")
  }
)
public class JMSConsumer  implements MessageListener {

  @Resource MessageDrivenContext context;
  
  @PostConstruct
  public void setup() {
    System.out.println("Construction of one MDB of type "+JMSConsumer.class.getName());
  }
  
  @Override
  public void onMessage(Message message) {
    String value;
    try {
      value = message.getBody(String.class);
      System.out.println("The message in the queue is "+value);
    } catch (JMSException e) {
      e.printStackTrace();
      System.out.println("Error while getting message back.");
    }
  }

  
}
