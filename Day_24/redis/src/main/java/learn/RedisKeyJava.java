package learn;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.Set;

public class RedisKeyJava {
  public static void main(String[] args) {
//Connecting to Redis server on localhost
    Jedis jedis = new Jedis("localhost");
    System.out.println("Connection to server sucessfully");
//store data in redis list
// Get the stored data and print it
    Set<String> list = jedis.keys("*");
    Iterator value = list.iterator();
    System.out.println(list.toString());
    while (value.hasNext()) {
      System.out.println("List of stored keys:: " + value.next());
    }
  }
}