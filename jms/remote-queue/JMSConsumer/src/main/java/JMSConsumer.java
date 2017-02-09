import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(name = "RemoteMDB", activationConfig = { 
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue"),
    @ActivationConfigProperty(propertyName = "connectorClassName", propertyValue = "org.apache.activemq.artemis.core.remoting.impl.netty.NettyConnectorFactory"),        
    @ActivationConfigProperty(propertyName = "connectionParameters", propertyValue = "host=127.0.0.1;port=61616")})
public class JMSConsumer  implements MessageListener {
  
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
