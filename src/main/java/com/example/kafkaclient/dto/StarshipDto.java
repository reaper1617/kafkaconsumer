package com.example.kafkaclient.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class StarshipDto {
    private String name;
    private String field;
    private LocalDateTime dateTime;
    private String localDateTimeString;
    private int count;

    public StarshipDto() {
        this.dateTime = LocalDateTime.now();
        this.localDateTimeString = dateTime.toString();
    }

    public StarshipDto(String name, String field, LocalDateTime dateTime) {
        this.name = name;
        this.field = field;
        this.dateTime = dateTime;
        this.localDateTimeString = this.dateTime.toString();
    }
}
