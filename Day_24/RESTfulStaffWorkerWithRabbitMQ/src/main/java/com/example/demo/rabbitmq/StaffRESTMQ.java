package com.example.demo.rabbitmq;

import com.rabbitmq.client.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Repository
public class StaffRESTMQ {
  public JSONObject produce(String queueName, String json) {
    ConnectionFactory connectionFactory = new ConnectionFactory();
    connectionFactory.setHost("localhost");
    Channel channel = null;
    try {
      Connection connection = connectionFactory.newConnection();
      channel = connection.createChannel();
      channel.queueDeclare(queueName, false, false, false, null);

      channel.basicPublish("", queueName, null, json.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    } catch (TimeoutException e) {
      e.printStackTrace();
    }

    return consume();
  }

  public JSONObject consume() {
    String queueName = "returnstaff";

    ConnectionFactory connectionFactory = new ConnectionFactory();
    connectionFactory.setHost("localhost");
    Connection connection = null;
    Channel channel = null;
    try {
      connection = connectionFactory.newConnection();
      channel = connection.createChannel();
      channel.queueDeclare(queueName, false, false, false, null);
      System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
      channel.basicQos(1);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (TimeoutException e) {
      e.printStackTrace();
    }
//    AtomicReference<JSONObject> jsonObject = new AtomicReference<>();
//    AtomicBoolean ack = new AtomicBoolean(false);
    Channel finalChannel = channel;
    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
      JSONObject jsonObject = null;
      try {
//        jsonObject.set((JSONObject) new JSONParser().parse(new String(delivery.getBody())));
        jsonObject = (JSONObject) new JSONParser().parse(new String(delivery.getBody()));
      } catch (ParseException e) {
        e.printStackTrace();
      }

//      System.out.println(" [x] Received '" + jsonObject.get().toJSONString() + "'");
      System.out.println(" [x] Received '" + jsonObject.toJSONString() + "'");

      finalChannel.basicAck(delivery.getEnvelope().getDeliveryTag(), false); // acknowledge
//      ack.set(true);
      System.out.printf(" [x] Done");
    };

    try {
      boolean autoAck = false;
      channel.basicConsume(queueName, false, deliverCallback, consumerTag -> {
      });
    } catch (IOException e) {
      e.printStackTrace();
    }

//    while (!ack.get()) {}

//    return (JSONObject) jsonObject.get();
    return null;
  }
}
