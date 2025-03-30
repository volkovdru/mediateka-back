package ru.volkovd.music_player.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDTO {
    private Long id;
    private String name;
    private int year;
    private String artist;
    private List<TrackDTO> tracks;
}
