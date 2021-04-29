import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;

public class RabbitMqManagementService {

    ConnectionFactory factory;
    Connection connection;
    Channel channel;

    public RabbitMqManagementService(String host){

        factory = new ConnectionFactory();
        factory.setHost(host);
        try{
            connection = factory.newConnection();
            channel = connection.createChannel();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void declareQueue(String queueName) throws IOException {
        channel.queueDeclare(queueName, false, false, false, null);
    }

    public void publishBoToHo(String queueName, String message) throws IOException {
        channel.basicPublish("", queueName, null, message.getBytes());
    }

    public void basicConsume(String queueName, DeliverCallback deliverCallback) throws IOException {
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }



}
