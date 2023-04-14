package com.obovkun.springkafka.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.obovkun.springkafka.KafkaProducerProperties;
import com.obovkun.springkafka.dto.KafkaMessageDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class KafkaSender {

    private KafkaTemplate<String, String> kafkaTemplate;
    private KafkaProducerProperties producerProperties;

    public void sendMessage(KafkaMessageDto kafkaMessageDto) {
        kafkaTemplate.send(producerProperties.getKafkaTopicStr(), kafkaMessageDto.message());
    }

}
