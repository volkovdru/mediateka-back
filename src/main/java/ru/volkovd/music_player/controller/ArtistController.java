package ru.volkovd.music_player.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.volkovd.music_player.dto.AlbumDTO;
import ru.volkovd.music_player.dto.ArtistDTO;
import ru.volkovd.music_player.mapper.ArtistMapper;
import ru.volkovd.music_player.model.Artist;
import ru.volkovd.music_player.service.ArtistService;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping
    public List<ArtistDTO> getAllArtists() {
        return artistService.getAllArtists();
    }

    @GetMapping("/{id}")
    public ArtistDTO getArtistById(@PathVariable Long id) {
        return ArtistMapper.toArtistDTO(artistService.getArtistById(id));
    }

    @GetMapping("/{id}/albums")
    public List<AlbumDTO> getAlbumsByArtist(@PathVariable Long id) {
        return artistService.getAlbumsByArtist(id);
    }

    @PostMapping
    public Artist createArtist(@RequestBody Artist artist) {
        return artistService.saveArtist(artist);
    }

    @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
    }
}