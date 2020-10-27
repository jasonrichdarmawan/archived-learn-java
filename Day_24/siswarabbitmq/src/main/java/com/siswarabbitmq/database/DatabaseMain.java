package com.siswarabbitmq.database;

import com.siswarabbitmq.database.rabbitmq.Receive;

//Receive (RabbitMQ) -> MyBatisService -> Database -> Send (RabbitMQ)

public class DatabaseMain {

    public static Receive receive = new Receive();

    public static void main(String[] args) {
        try{
            System.out.println(" [*] Waiting for messages..");
            receive.insertSiswa();
            receive.deleteSiswaById();
            receive.updateSiswaById();
        }catch (Exception e){
            System.out.println("Error DatabaseMain = " + e);
        }
    }
}
