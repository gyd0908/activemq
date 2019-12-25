package com.atguigu.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MQConsumer {
    public static final String MQ_URL = "tcp://192.168.64.128:61616";
    public static final String QUEUE_NAME = "queue0805";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(MQ_URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(message -> {

            if (message!=null && message instanceof  TextMessage){
                TextMessage textMessage =(TextMessage) message;
                try {
                    System.out.println("Consumer**"+textMessage.getText());
                } catch (JMSException e) {


                }
            }
        });

    }
}
