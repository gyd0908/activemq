package com.atguigu.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MQProducer {
    public static final String MQ_URL = "tcp://192.168.64.128:61616";
    public static final String QUEUE_NAME = "queue0805";

    public static void main(String[] args) throws JMSException {
        //建立工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(MQ_URL);
        //创建连接
        Connection connection = factory.createConnection();
        //开启连接
        connection.start();
        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地 队列
        Queue queue = session.createQueue(QUEUE_NAME);
        //创建生产者
        MessageProducer producer = session.createProducer(queue);
        for (int i = 1; i <= 3; i++) {
            TextMessage textMessage = session.createTextMessage("msg" + i);
            producer.send(textMessage);
        }
        //关闭连接 从里到外
        producer.close();
        session.close();
        connection.close();
        System.out.println("发送成功");


    }
}
