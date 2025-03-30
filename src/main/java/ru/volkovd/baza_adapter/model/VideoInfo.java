package ru.volkovd.baza_adapter.model;

import lombok.Data;

import java.util.List;

@Data
public class VideoInfo {
    private List<VideoStream> streams;
}