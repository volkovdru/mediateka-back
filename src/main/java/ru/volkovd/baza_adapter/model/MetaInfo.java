package ru.volkovd.baza_adapter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MetaInfo {
    private VideoInfo video;
    private AudioInfo audio;
    @JsonProperty("playtime_seconds")
    private Long playtimeSeconds;
    @JsonProperty("file_size")
    private Long fileSize;
    @JsonProperty("mime_type")
    private String mimeType;
    private Long bitrate;
    @JsonProperty("playtime_string")
    private String playtimeString;
}