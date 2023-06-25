package com.obovkun.springkafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.obovkun.springkafka.models.message.KafkaMessageAvro;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class KafkaConsumerListener {

    @KafkaListener(topics = "${ms-consumer.topic.kafka-topic-message-avro}", groupId = "${spring.kafka.consumer.group-id}")
    public void receive(KafkaMessageAvro message) {
        log.info("Received message: " + message);
    }

}
