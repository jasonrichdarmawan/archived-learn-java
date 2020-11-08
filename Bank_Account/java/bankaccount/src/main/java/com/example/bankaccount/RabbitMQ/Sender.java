package com.example.bankaccount.RabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {

  public final RabbitTemplate rabbitTemplate;

  public Sender(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }
}
