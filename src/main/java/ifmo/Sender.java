package ifmo;

//import org.apache.activemq.ActiveMQConnection;
//import org.apache.activemq.ActiveMQConnectionFactory;

import javax.ejb.Stateless;
import javax.jms.*;

/**
 * Created by Богдана on 24.11.2017.
 */
@Stateless
public class Sender {
  //  String url = ActiveMQConnection.DEFAULT_BROKER_URL;
  /*  private String topic = "in_out";
    public void sendMsg(String text){
        try{
            ConnectionFactory activeMQfact = new ActiveMQConnectionFactory(url);
            Connection connection =activeMQfact.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic(topic);
            MessageProducer prod =session.createProducer(destination);
            Message msg = session.createTextMessage(text);
            prod.send(msg);
        }catch (Exception e){}
    }*/
}
