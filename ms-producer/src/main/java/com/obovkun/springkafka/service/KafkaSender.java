package com.obovkun.springkafka.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.obovkun.springkafka.KafkaProducerProperties;
import com.obovkun.springkafka.dto.KafkaMessageDto;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class KafkaSender {

    private KafkaTemplate<String, String> kafkaTemplate;
    private KafkaProducerProperties producerProperties;

    public void sendMessage(KafkaMessageDto kafkaMessageDto) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(producerProperties.getKafkaTopicStr(),
                kafkaMessageDto.message());
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                handleSuccess(result);
            } else {
                handleFailure(result, ex);
            }
        });
    }

    private void handleSuccess(SendResult<String, String> result) {
        log.info("Message was sent successfully: {}", result);
    }

    private void handleFailure(SendResult<String, String> result, Throwable ex) {
        log.error("Unable to send the message due to: {}", ex.getMessage());
    }

}
