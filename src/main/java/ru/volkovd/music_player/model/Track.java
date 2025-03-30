package ru.volkovd.music_player.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "album")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "artist")
    private Artist artist;

    private String src; // Путь к MP3-файлу
}