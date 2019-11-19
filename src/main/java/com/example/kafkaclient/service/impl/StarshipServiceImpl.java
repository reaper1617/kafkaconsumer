package com.example.kafkaclient.service.impl;

import com.example.kafkaclient.dto.StarshipDto;
import com.example.kafkaclient.service.api.StarshipService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StarshipServiceImpl implements StarshipService {

    private final KafkaTemplate<Long,StarshipDto> kafkaStarshipTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public StarshipServiceImpl(KafkaTemplate<Long, StarshipDto> kafkaStarshipTemplate, ObjectMapper objectMapper) {
        this.kafkaStarshipTemplate = kafkaStarshipTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void send(StarshipDto starshipDto) {
        kafkaStarshipTemplate.send("server.starship", starshipDto);
    }

    @Override
    @KafkaListener(id = "Starship", topics = {"server.starship"}, containerFactory = "singleFactory")
    public void consume(StarshipDto starshipDto) {
        log.info(" => consumed {}", starshipDto);
    }

    private String writeValueAsString(StarshipDto starshipDto){
        try {
            return objectMapper.writeValueAsString(starshipDto);
        }
        catch (JsonProcessingException e){
            log.info("Error while parsing {}", starshipDto, e);
            throw new RuntimeException(e);
        }
    }
}
