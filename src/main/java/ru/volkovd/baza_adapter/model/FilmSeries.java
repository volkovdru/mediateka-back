package ru.volkovd.baza_adapter.model;

import lombok.Data;

@Data
public class FilmSeries {
    private Long id;
    private String name;
    private String international_name;
    private String poster;
    private int year;
}
