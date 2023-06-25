package com.obovkun.springkafka.dto;

public record KafkaMessageDto(
        String message, 
        String type,
        String status) {
}
