package com.siswarabbitmq.restapi;

import com.siswarabbitmq.restapi.rabbitmq.RestApiReceive;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Program Springboot, RabbitMQ dan MyBatis
//PostMan -> RestApiController -> RestApiSend (RabbitMQ)

@SpringBootApplication
public class RestApiMain {
	public static void main(String[] args) {
		SpringApplication.run(RestApiMain.class, args);
		RestApiReceive restApiReceive = new RestApiReceive();
		try {
			restApiReceive.receiveFromDatabase();
		}catch (Exception e){
			System.out.println(e);
		}
	}
}
