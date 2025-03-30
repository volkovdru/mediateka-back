package ru.volkovd.baza_adapter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MovieResponse {
    @JsonProperty("data")
    private MovieData data;
}