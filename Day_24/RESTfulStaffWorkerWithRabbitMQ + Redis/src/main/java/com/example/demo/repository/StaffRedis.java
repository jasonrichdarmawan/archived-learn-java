package com.example.demo.repository;

import com.example.demo.model.StaffWorkerModel;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import java.util.List;

@Repository
public class StaffRedis {
  Jedis jedis;

  public StaffRedis() {
    jedis = new Jedis("localhost");
  }

  public List<String> getStaffs() {
    return jedis.lrange("staffs", 0, 100);
  }

  public String getStaffById(int id) {
    return jedis.lindex("staffs", id);
  }

  public Long postStaff(String payload) {
    return jedis.lpush("staffs", payload);
  }

  public String putStaffById(int id, String payload) {
    return jedis.lset("staffs", id, payload);
  }

  public String deleteStaffById(int id) {
    return jedis.lset("staffs", id, "");
  }

  public Long deleteStaffs() {
    return jedis.del("staffs");
  }
}
