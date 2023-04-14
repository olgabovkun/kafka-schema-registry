package com.obovkun.springkafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class KafkaConsumerListener {

    @KafkaListener(topics = "${ms-consumer.topic.kafka-topic-str}", groupId = "${kafka.consumer.group-id}")
    public void receive(String message) {
        log.info("Received message: " + message);
    }
}
