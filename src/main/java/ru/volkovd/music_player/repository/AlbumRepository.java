package ru.volkovd.music_player.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.volkovd.music_player.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    Album findByName(String name);

    @Query("SELECT a FROM Album a JOIN a.artist ar WHERE a.name = :albumName AND ar.name = :artistName")
    Album findByArtistNameAndAlbumName(@Param("albumName") String albumName, @Param("artistName") String artistName);
}