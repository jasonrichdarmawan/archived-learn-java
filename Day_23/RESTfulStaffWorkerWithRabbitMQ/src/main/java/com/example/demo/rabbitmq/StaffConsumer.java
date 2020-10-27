package com.example.demo.rabbitmq;

import com.example.demo.model.StaffWorkerModel;
import com.example.demo.repository.StaffDao;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Configuration
public class StaffConsumer {
  @Autowired
  private StaffDao staffDao;

  public void doPostStaff(String payload) {
    StaffWorkerModel staff = new Gson().fromJson(payload, StaffWorkerModel.class);
    this.staffDao.postStaff(staff);
  }

  public void doPutStaffById(String payload) {
    StaffWorkerModel staff = new Gson().fromJson(payload, StaffWorkerModel.class);
    this.staffDao.putStaffById(staff);
  }

  public void doDeleteStaffById(String payload) {
    StaffWorkerModel staff = new Gson().fromJson(payload, StaffWorkerModel.class);
    int id = staff.getIDKaryawan();
    this.staffDao.deleteStaffById(id);
  }

  @Bean
  public void consume() {
    String queueName = "staff";

    ConnectionFactory connectionFactory = new ConnectionFactory();
    connectionFactory.setHost("localhost");
    Connection connection = null;
    Channel channel = null;
    try {
      connection = connectionFactory.newConnection();
      channel = connection.createChannel();
      channel.queueDeclare(queueName, false, false, false, null);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (TimeoutException e) {
      e.printStackTrace();
    }
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
      JSONObject jsonObject = null;
      try {
        jsonObject = (JSONObject) new JSONParser().parse(new String(delivery.getBody()));
      } catch (ParseException e) {
        e.printStackTrace();
      }

      System.out.println(" [x] Received '" + jsonObject.toJSONString() + "'");

      if (jsonObject.get("type").equals("postStaff")) {
        doPostStaff(jsonObject.get("payload").toString());
      } else if (jsonObject.get("type").equals("putStaff")) {
        doPutStaffById(jsonObject.get("payload").toString());
      } else if (jsonObject.get("type").equals("deleteStaffById")) {
        doDeleteStaffById(jsonObject.get("payload").toString());
      } else if (jsonObject.get("type").equals("deleteStaffs")) {
        this.staffDao.deleteStaffs();
      }

      System.out.printf(" [x] Done");
    };
    try {
      channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
