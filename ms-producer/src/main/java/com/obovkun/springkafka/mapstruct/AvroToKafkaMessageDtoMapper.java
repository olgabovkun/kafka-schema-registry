package com.obovkun.springkafka.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.obovkun.springkafka.dto.KafkaMessageDto;
import com.obovkun.springkafka.models.message.KafkaMessageAvro;

@Mapper
public interface AvroToKafkaMessageDtoMapper {

    AvroToKafkaMessageDtoMapper INSTANCE = Mappers.getMapper( AvroToKafkaMessageDtoMapper.class );

    KafkaMessageDto avroToKafkaMessageDto(KafkaMessageAvro kafkaMessageAvro);

    KafkaMessageAvro kafkaMessageDtoToAvro(KafkaMessageDto kafkaMessageDto);
    
}
