package ru.volkovd.music_player.service;

import ru.volkovd.music_player.dto.AlbumDTO;
import ru.volkovd.music_player.mapper.AlbumMapper;
import ru.volkovd.music_player.model.Album;
import ru.volkovd.music_player.model.Artist;
import ru.volkovd.music_player.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final AlbumMapper albumMapper;

    public List<AlbumDTO> getAllAlbums() {
        List<Album> albums = albumRepository.findAll();
        return albumMapper.toAlbumDTOList(albums);
    }

    public Album findByArtistNameAndAlbumName(String albumName, String artistName) {
        return albumRepository.findByArtistNameAndAlbumName(albumName, artistName);
    }

    public Album getAlbumById(Long id) {
        return albumRepository.findById(id).orElse(null);
    }

    public Album getAlbumByName(String name) {
        return albumRepository.findByName(name);
    }

    public Album saveAlbum(Album album) {
        return albumRepository.save(album);
    }

    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }

    public Album saveAlbumIfNotExists(String albumName, Artist artist) {
        Album album = findByArtistNameAndAlbumName(albumName, artist.getName());
        if (album == null) {
            Album newAlbum = new Album();
            newAlbum.setArtist(artist);
            newAlbum.setName(albumName);
            return this.saveAlbum(newAlbum);
        } else {
            return album;
        }
    }


}