package com.nttdata.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.nttdata.model.Client;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaProducer {
  
  private final KafkaTemplate<String, Object> kafkaTemplate;

  public KafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
      this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(Client client) {
      log.info("Producing message: {}", client.toString());
      this.kafkaTemplate.send("TOPIC-CLIENT", client);
  }

}
