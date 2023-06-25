package com.obovkun.springkafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

import lombok.AllArgsConstructor;

@EnableKafka
@AllArgsConstructor
@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic topicAvroMessage() {
        return TopicBuilder.name("topic-avro-message").build();
    }
}
