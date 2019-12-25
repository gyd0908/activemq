package com.atguigu.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MQConsumer_Topic {
    public static final String MQ_URL = "tcp://192.168.64.128:61616";
    public static final String TOPIC_NAME = "topic0805";

    public static void main(String[] args) throws JMSException {
        //创建工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(MQ_URL);
        //建立连接
        Connection connection = factory.createConnection();
        //开启连接
        connection.start();
        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地
        Topic topic = session.createTopic(TOPIC_NAME);
        //创建消费者
        MessageConsumer consumer = session.createConsumer(topic);
        //设置消息监听器
        consumer.setMessageListener(message -> {

            if (message != null && message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("Consumer**" + textMessage.getText());
                } catch (JMSException e) {

                }
            }
        });

    }
}
