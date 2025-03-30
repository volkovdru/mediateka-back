package ru.volkovd.music_player.repository;

import ru.volkovd.music_player.model.Track;
import org.springframework.content.commons.repository.ContentStore;
import org.springframework.stereotype.Component;

@Component
public interface TrackRestRepository extends ContentStore<Track, String> {
}