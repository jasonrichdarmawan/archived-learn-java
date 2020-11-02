package com.example.demo.learn;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class ReceiverWorker {
  private final static String QUEUE_NAME = "hello";

  private static void doWork(String task) throws InterruptedException {
    for (char ch : task.toCharArray()) {
      if (ch == '.') Thread.sleep(1000);
    }
  }

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
    // Acknowlegdement
    channel.basicQos(1);
    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
      String message = new String(delivery.getBody(), "UTF-8");
      System.out.println(" [x] Received '" + message + "'");
      try {
        doWork(message);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false); // acknowledge
        System.out.println(" [x] Done");
      }
    };
    boolean autoAck = false; // acknowledgment is covered below
    channel.basicConsume(QUEUE_NAME, autoAck, deliverCallback, consumerTag -> {
    });
  }

}