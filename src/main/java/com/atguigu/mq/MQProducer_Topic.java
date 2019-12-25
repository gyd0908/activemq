package com.atguigu.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MQProducer_Topic {
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
       //创建目的地 主题topic  先启动消费者在启动生产者，不然消息会变成废消息
        Topic topic = session.createTopic(TOPIC_NAME);
        //创建生产者
        MessageProducer producer = session.createProducer(topic);
        for (int i = 1; i <= 3; i++) {
            TextMessage textMessage = session.createTextMessage("msg" + i);
            producer.send(textMessage);
        }
        //关闭连接
        producer.close();
        session.close();
        connection.close();
        System.out.println("发送成功");


    }
}
