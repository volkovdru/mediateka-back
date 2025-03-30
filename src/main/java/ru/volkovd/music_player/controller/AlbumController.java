package ru.volkovd.music_player.controller;

import ru.volkovd.music_player.dto.AlbumDTO;
import ru.volkovd.music_player.mapper.AlbumMapper;
import ru.volkovd.music_player.model.Album;
import ru.volkovd.music_player.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;
    private final AlbumMapper albumMapper;

    @GetMapping
    public List<AlbumDTO> getAllAlbums() {
        return albumService.getAllAlbums();
    }

    @GetMapping("/{album}")
    public AlbumDTO getAlbumById(@PathVariable Album album) {
        return albumMapper.toAlbumDTO(album);
    }

    @PostMapping
    public Album createAlbum(@RequestBody Album album) {
        return albumService.saveAlbum(album);
    }

    @DeleteMapping("/{id}")
    public void deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
    }
}