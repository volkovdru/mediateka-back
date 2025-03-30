package ru.volkovd.music_player.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data // Геттеры, сеттеры, toString, equals, hashCode
@NoArgsConstructor // Конструктор без аргументов
@AllArgsConstructor // Конструктор со всеми аргументами
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Album> albums;

    public Artist(String name) {
        this.name = name;
    }
}