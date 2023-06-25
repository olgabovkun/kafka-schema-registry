package com.obovkun.springkafka.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.obovkun.springkafka.KafkaProducerProperties;
import com.obovkun.springkafka.models.message.KafkaMessageAvro;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class KafkaSender {

    private KafkaTemplate<String, KafkaMessageAvro> kafkaTemplate;
    private KafkaProducerProperties producerProperties;

    public void sendMessage(KafkaMessageAvro kafkaMessageAvro) {
        CompletableFuture<SendResult<String, KafkaMessageAvro>> future = kafkaTemplate.send(producerProperties.getKafkaTopicMessageAvro(), kafkaMessageAvro);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                handleSuccess(result);
            } else {
                handleFailure(result, ex);
            }
        });
    }

    private void handleSuccess(SendResult<String, KafkaMessageAvro> result) {
        log.info("Message was sent successfully: {}", result);
    }

    private void handleFailure(SendResult<String, KafkaMessageAvro> result, Throwable ex) {
        log.error("Unable to send the message due to: {}", ex.getMessage());
    }

}
