package ru.volkovd.baza_adapter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FileInfo {
    private Long id;
    private String name;
    private String online;
    private String download;
    private String m3u;
    private String rss;
    private MetaInfo metainfo;
    private Long size;
    private List<String> frames;
    @JsonProperty("translation")
    private List<String> translation;
    private String viewed;

    public void setTranslation(String translation) {
        this.translation = new ArrayList<>();
        if (!translation.isEmpty())
            this.translation.add(translation);
    }
}