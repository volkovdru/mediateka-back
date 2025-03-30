package ru.volkovd.music_player.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.volkovd.music_player.model.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Artist findByName(String name);
}