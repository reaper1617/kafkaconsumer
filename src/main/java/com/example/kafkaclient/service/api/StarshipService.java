package com.example.kafkaclient.service.api;

import com.example.kafkaclient.dto.StarshipDto;

public interface StarshipService {
    public void send(StarshipDto starshipDto);
    public void consume(StarshipDto starshipDto);
}
