package ru.volkovd.music_player.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.volkovd.music_player.dto.AlbumDTO;
import ru.volkovd.music_player.dto.ArtistDTO;
import ru.volkovd.music_player.mapper.AlbumMapper;
import ru.volkovd.music_player.mapper.ArtistMapper;
import ru.volkovd.music_player.model.Artist;
import ru.volkovd.music_player.repository.ArtistRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final AlbumMapper albumMapper;

    @Transactional(readOnly = true)
    public List<ArtistDTO> getAllArtists() {
        return artistRepository.findAll().stream()
                .map(ArtistMapper::toArtistDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public Artist getArtistById(Long id) {
        return artistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Artist not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public Artist getArtistByName(String name) {
        return artistRepository.findByName(name);
    }

    public Artist saveArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    @Transactional
    public void deleteArtist(Long id) {
        Artist artist = getArtistById(id);
        artistRepository.delete(artist);
    }

    @Transactional(readOnly = true)
    public List<AlbumDTO> getAlbumsByArtist(Long artistId) {
        Artist artist = getArtistById(artistId);
        return artist.getAlbums().stream()
                .map(albumMapper::toAlbumDTO)
                .toList();
    }

    @Transactional
    public Artist saveArtistIfNotExists(String artistName) {
        return artistRepository.findByName(artistName);
    }
}