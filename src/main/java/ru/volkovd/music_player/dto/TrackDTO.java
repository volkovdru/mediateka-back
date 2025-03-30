package ru.volkovd.music_player.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@AllArgsConstructor
public class TrackDTO {

    private Long id;
    private String title;
    private String album;
    private String artist;
    private String src;
}
