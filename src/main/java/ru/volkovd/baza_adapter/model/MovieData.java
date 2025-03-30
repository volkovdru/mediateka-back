package ru.volkovd.baza_adapter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class MovieData {
    private Long id;
    private String name;

    @JsonProperty("international_name")
    private String internationalName;

    private String description;
    private Integer year;
    private String genres;
    private String countries;

    @JsonProperty("rating_kp")
    private Float ratingKp;

    @JsonProperty("rating_bz")
    private Float ratingBz;

    private List<String> posters;
    private Boolean favorite;

    @JsonProperty("watch_later")
    private Boolean watchLater;

    @JsonProperty("can_rate")
    private Boolean canRate;

    private List<FileInfo> files;

    @JsonProperty("film_series")
    private List<FilmSeries> filmSeries;

    @JsonProperty("participants")
    private List<Participant> participants;

    public void setParticipants(Map<Integer, Participant> participantsMap) {
        if (participantsMap != null) {
            this.participants = new ArrayList<>();
            for (Map.Entry<Integer, Participant> entry : participantsMap.entrySet()) {
                Participant participant = entry.getValue();
                participant.setId(entry.getKey());
                this.participants.add(participant);
            }
        } else {
            this.participants = new ArrayList<>();
        }
    }
}