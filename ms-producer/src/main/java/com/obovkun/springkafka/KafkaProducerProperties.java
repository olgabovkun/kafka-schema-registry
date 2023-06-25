package com.obovkun.springkafka;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "ms-producer.topic")
public class KafkaProducerProperties {
    private String kafkaTopicMessageAvro;
}
