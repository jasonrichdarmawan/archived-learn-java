package com.siswarabbitmq.database.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.siswarabbitmq.database.service.MyBatisService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Receive {

    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private MyBatisService myBatisService = new MyBatisService();
//    private Adapter adapter = new Adapter();


    public void connectRabbitMQ() throws IOException, TimeoutException {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
    }

    public void insertSiswa() {
        try{
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("insertSiswa", false, false, false, null);
            //System.out.println(" [*] Waiting for messages..");
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String siswaString = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + siswaString + "'");
                try {
                    myBatisService.insertSiswa(siswaString);
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            };
            channel.basicConsume("insertSiswa", true, deliverCallback, consumerTag -> { });
        }catch (Exception e) {
            System.out.println("Error insertSiswa = " + e);
        }
    }

    public void deleteSiswaById() {
        try{
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("deleteSiswaById", false, false, false, null);
            //System.out.println(" [*] Waiting for messages..");
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String idString = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + idString + "'");
                try {
                    myBatisService.deleteSiswa(idString);
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            };
            channel.basicConsume("deleteSiswaById", true, deliverCallback, consumerTag -> { });
        }catch (Exception e) {
            System.out.println("Error insertSiswa = " + e);
        }
    }

    public void updateSiswaById() {
        try{
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("updateSiswaById", false, false, false, null);
            //System.out.println(" [*] Waiting for messages..");
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String siswaString = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + siswaString + "'");
                try {
                    myBatisService.updateSiswa(siswaString);
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            };
            channel.basicConsume("updateSiswaById", true, deliverCallback, consumerTag -> { });
        }catch (Exception e) {
            System.out.println("Error insertSiswa = " + e);
        }
    }
}
