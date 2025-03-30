package ru.volkovd.baza_adapter.model;

import lombok.Data;

import java.util.List;

@Data
public class AudioInfo {
    private List<AudioStream> streams;
}