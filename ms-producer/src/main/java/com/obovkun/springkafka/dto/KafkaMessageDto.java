package com.obovkun.springkafka.dto;


public record KafkaMessageDto(
        String message, 
        String content,
        String type) {
}
