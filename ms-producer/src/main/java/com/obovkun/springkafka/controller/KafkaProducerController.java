package com.obovkun.springkafka.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obovkun.springkafka.dto.KafkaMessageDto;
import com.obovkun.springkafka.service.KafkaSender;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/producer")
public class KafkaProducerController {

    private final KafkaSender kafkaSender;
    
    @PostMapping("send-message")
    public void sendMessage(@RequestBody KafkaMessageDto kafkaMessageDto) {
        kafkaSender.sendMessage(kafkaMessageDto);
    }

}
