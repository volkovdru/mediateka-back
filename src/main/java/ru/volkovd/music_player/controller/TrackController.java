package ru.volkovd.music_player.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.volkovd.music_player.model.Track;
import ru.volkovd.music_player.dto.TrackDTO;
import ru.volkovd.music_player.service.ArtistService;
import ru.volkovd.music_player.service.TrackService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/tracks")
@RequiredArgsConstructor
public class TrackController {

    private final TrackService trackService;
    private final ArtistService artistService;

    @Value("${upload.directory}") // Директория для загрузки файлов (настраивается в application.properties)
    private String uploadDirectory;

    @GetMapping
    public List<TrackDTO> getAllTracks() {
        return trackService.getAllTracks();
    }

    @GetMapping("/{id}")
    public Track getTrackById(@PathVariable Long id) {
        return trackService.getTrackById(id);
    }

    @PostMapping
    public Track createTrack(
            @RequestParam("title") String title,
            @RequestParam(value = "albumId", required = false) Long albumId,
            @RequestParam(value = "artistId", required = false) Long artistId,
            @RequestParam("file") MultipartFile file) throws IOException {


        return trackService.processTrack(file);
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("files") MultipartFile[] files) {

        try {
            // Создаем директорию, если она не существует
            Path uploadPath = Paths.get(uploadDirectory);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Обрабатываем каждый файл
            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    continue; // Пропускаем пустые файлы
                }

                if (!file.getContentType().equals("audio/mpeg")) {
                    continue;
                }

                Track track = trackService.processTrack(file);
            }

            return "Файлы успешно загружены: " + Arrays.toString(files);
        } catch (IOException e) {
            e.printStackTrace();
            return "Ошибка при загрузке файлов: " + e.getMessage();
        }
    }


    @DeleteMapping("/{id}")
    public void deleteTrack(@PathVariable Long id) throws IOException {
        trackService.deleteTrack(id);
    }
}