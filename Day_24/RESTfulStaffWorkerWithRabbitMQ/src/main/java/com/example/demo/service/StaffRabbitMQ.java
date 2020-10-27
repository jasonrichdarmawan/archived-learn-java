package com.example.demo.service;

import com.example.demo.rabbitmq.StaffRESTMQ;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffRabbitMQ {
  @Autowired
  private StaffRESTMQ staffRESTMQ;

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

  public JSONObject postStaff(String requestBody) {
    return this.staffRESTMQ.produce("staff", stringToJSONObject("postStaff", requestBody).toJSONString());
  }

  public JSONObject putStaffById(String requestBody) {
    return this.staffRESTMQ.produce("staff", stringToJSONObject("putStaffById", requestBody).toJSONString());
  }

  public JSONObject deleteStaffById(String requestBody) {
    return this.staffRESTMQ.produce("staff", stringToJSONObject("deleteStaffById", requestBody).toJSONString());
  }

  public JSONObject deleteStaffs() {
    return this.staffRESTMQ.produce("staff", "{\"type\": \"deleteStaffs\"}");
  }

}
