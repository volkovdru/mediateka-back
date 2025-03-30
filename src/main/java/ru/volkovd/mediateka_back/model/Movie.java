package ru.volkovd.mediateka_back.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long idBz;
    private String name;
    private String internationalName;
    private String description;
    private int year;

    private List<Long> genres;
    private List<Long> countries;

    private Float ratingBz;
    private Float ratingKp;

    private List<String> posters;

    @Builder
    public Movie(Long id, Long idBz, String name, String internationalName, String description, int year, List<Long> genres, List<Long> countries, Float ratingBz, Float ratingKp, List<String> posters) {
        this.id = id;
        this.idBz = idBz;
        this.name = name;
        this.internationalName = internationalName;
        this.description = description;
        this.year = year;
        this.genres = genres;
        this.countries = countries;
        this.ratingBz = ratingBz;
        this.ratingKp = ratingKp;
        this.posters = posters;
    }
}