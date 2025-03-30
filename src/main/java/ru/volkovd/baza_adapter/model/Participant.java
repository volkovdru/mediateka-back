package ru.volkovd.baza_adapter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Participant {
    private Integer id;
    private String name;

    @JsonProperty("international_name")
    private String internationalName;

    private String info;
    private String photo;
    private String role;
}