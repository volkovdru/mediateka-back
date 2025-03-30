package ru.volkovd.mediateka_back.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Genre {

    @Id
    private Long id;

    private String name;

    @Builder
    public Genre(String name) {
        this.name = name;
    }
}