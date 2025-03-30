package ru.volkovd.music_player.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.volkovd.music_player.model.Track;

public interface TrackRepository extends JpaRepository<Track, Long> {
}