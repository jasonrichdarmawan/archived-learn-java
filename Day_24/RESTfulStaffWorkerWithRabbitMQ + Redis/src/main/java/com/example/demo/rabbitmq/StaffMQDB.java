package com.example.demo.rabbitmq;

import com.example.demo.model.StaffWorkerModel;
import com.example.demo.repository.StaffDao;
import com.example.demo.repository.StaffMyBatis;
import com.example.demo.repository.StaffRedis;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.SerializationUtils;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

@Configuration
public class StaffMQDB {
//  @Autowired
//  private StaffDao staffDao;

  @Autowired
  private StaffMyBatis staffMyBatis;

  @Autowired
  private StaffRedis staffRedis;

  public void doGetStaffs(String queueName, Channel channel) {
//    List<StaffWorkerModel> responseStaffs = this.staffMyBatis.getStaffs(); // v2
    List<String> strings = this.staffRedis.getStaffs();
    List<StaffWorkerModel> responseStaffs = new ArrayList<>();

    for (int i = 0; i < strings.size(); i++) {
      StaffWorkerModel tempStaff = new Gson().fromJson(strings.get(i), StaffWorkerModel.class);
      tempStaff.setIDKaryawan(i + 1);
      responseStaffs.add(tempStaff);
    }

    JSONObject response = new JSONObject();
    if (responseStaffs.size() > 0) {
      /**
       * { response: boolean, payload: JSONArray }
       */
      response.put("response", true);

      // payload
      JSONArray responsePayload = new JSONArray();
      for (StaffWorkerModel staff : responseStaffs) {
        JSONObject temp = new JSONObject();
        temp.put("IDKaryawan", staff.getIDKaryawan());
        temp.put("Nama", staff.getNama());
        temp.put("TunjanganPulsa", staff.getTunjanganPulsa());
        temp.put("GajiPokok", staff.getGajiPokok());
        temp.put("AbsensiHari", staff.getAbsensiHari());
        temp.put("TunjanganMakan", staff.HitungTunjanganMakan());
        temp.put("Email", staff.getEmail());
        responsePayload.add(temp);
      }
      response.put("payload", responsePayload);
    }

    try {
      channel.basicPublish("", String.format("return%s", queueName), null, response.toString().getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void doGetStaffById(String payload, String queueName, Channel channel) {
//    StaffWorkerModel responseStaff = this.staffMyBatis.getStaffById(Integer.parseInt(payload)); // v2
    StaffWorkerModel responseStaff = new Gson().fromJson(this.staffRedis.getStaffById(Integer.parseInt(payload) - 1), StaffWorkerModel.class);
    responseStaff.setIDKaryawan(Integer.parseInt(payload));

    JSONObject response = new JSONObject();
    JSONObject responsePayload = new JSONObject();
    if (responseStaff != null) {
      response.put("response", true);
      responsePayload.put("IDKaryawan", responseStaff.getIDKaryawan());
      responsePayload.put("Nama", responseStaff.getNama());
      responsePayload.put("TunjanganPulsa", responseStaff.getTunjanganPulsa());
      responsePayload.put("GajiPokok", responseStaff.getGajiPokok());
      responsePayload.put("AbsensiHari", responseStaff.getAbsensiHari());
      responsePayload.put("TunjanganMakan", responseStaff.HitungTunjanganMakan());
      responsePayload.put("Email", responseStaff.getEmail());
      response.put("payload", responsePayload);
    } else {
      response.put("response", false);
    }

    try {
      channel.basicPublish("", String.format("return%s", queueName), null, response.toString().getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void doPostStaff(String payload, String queueName, Channel channel) {
    StaffWorkerModel requestStaff = new Gson().fromJson(payload, StaffWorkerModel.class);

//    StaffWorkerModel responseStaff = this.staffDao.postStaff(requestStaff); // v1
//    StaffWorkerModel responseStaff = this.staffMyBatis.postStaff(requestStaff); // v2
    Long index = this.staffRedis.postStaff(payload);

    JSONObject response = new JSONObject();
    JSONObject responsePayload = new JSONObject();
    if (
//            responseStaff != null // v2
            index != null
    ) {
      response.put("response", true);
//      responsePayload.put("IDKaryawan", responseStaff.getIDKaryawan()); // v2
      responsePayload.put("IDKaryawan", index);
      response.put("payload", responsePayload);
    } else {
      response.put("response", false);
    }

    try {
      channel.basicPublish("", String.format("return%s", queueName), null, response.toString().getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void doPutStaffById(String payload, String queueName, Channel channel) {
    StaffWorkerModel requestStaff = new Gson().fromJson(payload, StaffWorkerModel.class);
//    this.staffDao.putStaffById(requestStaff); v1

//    int rowAffected = this.staffMyBatis.putStaffById(requestStaff); v2

    JSONObject response = new JSONObject();
    if (
//            rowAffected > 0 v2
            this.staffRedis.putStaffById(requestStaff.getIDKaryawan() - 1, payload) == "OK"
    ) {
      response.put("response", true);
    } else {
      response.put("response", false);
    }

    try {
      channel.basicPublish("", String.format("return%s", queueName), null, response.toString().getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void doDeleteStaffById(String payload, String queueName, Channel channel) {
    StaffWorkerModel requestStaff = new Gson().fromJson(payload, StaffWorkerModel.class);
    int id = requestStaff.getIDKaryawan();
//    this.staffDao.deleteStaffById(id);
//    int rowAffected = this.staffMyBatis.deleteStaffById(id); v2

    JSONObject response = new JSONObject();
    if (
//            rowAffected > 0 v2
            this.staffRedis.deleteStaffById(requestStaff.getIDKaryawan() - 1) == "OK"
    ) {
      response.put("response", true);
    } else {
      response.put("response", false);
    }

    try {
      channel.basicPublish("", String.format("return%s", queueName), null, response.toString().getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void doDeleteStaffs(String queueName, Channel channel) {
//    this.staffDao.deleteStaffs();
//    int rowAffected = this.staffMyBatis.deleteStaffs(); v2
    JSONObject response = new JSONObject();
    if (
//            rowAffected > 0 v2
            this.staffRedis.deleteStaffs() > 0
    ) {
      response.put("response", true);
    } else {
      response.put("response", false);
    }

    try {
      channel.basicPublish("", String.format("return%s", queueName), null, response.toString().getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
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
      System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
      channel.basicQos(1);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (TimeoutException e) {
      e.printStackTrace();
    }
    Channel finalChannel = channel;
    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
      JSONObject jsonObject = null;
      try {
        jsonObject = (JSONObject) new JSONParser().parse(new String(delivery.getBody()));
      } catch (ParseException e) {
        e.printStackTrace();
      }

      System.out.println(" [x] Received '" + jsonObject.toJSONString() + "'");

      if (jsonObject.get("type").equals("getStaffs")) {
        doGetStaffs(queueName, finalChannel);
      } else if (jsonObject.get("type").equals("getStaffById")) {
        doGetStaffById(jsonObject.get("payload").toString(), queueName, finalChannel);
      } else if (jsonObject.get("type").equals("postStaff")) {
        doPostStaff(jsonObject.get("payload").toString(), queueName, finalChannel);
      } else if (jsonObject.get("type").equals("putStaffById")) {
        doPutStaffById(jsonObject.get("payload").toString(), queueName, finalChannel);
      } else if (jsonObject.get("type").equals("deleteStaffById")) {
        doDeleteStaffById(jsonObject.get("payload").toString(), queueName, finalChannel);
      } else if (jsonObject.get("type").equals("deleteStaffs")) {
        doDeleteStaffs(queueName, finalChannel);
      }
      finalChannel.basicAck(delivery.getEnvelope().getDeliveryTag(), false); // acknowledge
      System.out.printf(" [x] Done");
    };
    try {
      boolean autoAck = false;
      channel.basicConsume(queueName, false, deliverCallback, consumerTag -> {
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
