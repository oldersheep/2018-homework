package com.xxx.distributed;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Description p2p模型，消费端从queue中取出消息
 * @Date 2019/6/9 15:21
 * @Version 1.0
 */
public class JMSTopicConsumer {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.0.111:61616");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("myTopic");
            MessageConsumer consumer = session.createConsumer(topic);
            TextMessage message = (TextMessage) consumer.receive();

            System.out.println(message.getText());

            session.commit();
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
