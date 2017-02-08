import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/myqueue")
    //@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/myremotequeue")
    
}) 
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
