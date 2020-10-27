package com.example.demo.service;

import com.example.demo.rabbitmq.StaffProducer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffRabbitMQ {
  @Autowired
  private StaffProducer staffProducer;

  public JSONObject stringToJSONObject(String type, String requestBody) {
    JSONObject jsonObject = new JSONObject();
    JSONObject payload = null;
    try {
      payload = (JSONObject) new JSONParser().parse(requestBody);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    jsonObject.put("type", type);
    jsonObject.put("payload", payload);

    return jsonObject;
  }

  public void postStaff(String requestBody) {
    this.staffProducer.produce("staff", stringToJSONObject("postStaff", requestBody).toJSONString());
  }

  public void putStaffById(String requestBody) {
    this.staffProducer.produce("staff", stringToJSONObject("putStaff", requestBody).toJSONString());
  }

  public void deleteStaffById(String requestBody) {
    this.staffProducer.produce("staff", stringToJSONObject("deleteStaffById", requestBody).toJSONString());
  }

  public void deleteStaffs() {
    this.staffProducer.produce("staff", "{\"type\": \"deleteStaffs\"}");
  }

}
