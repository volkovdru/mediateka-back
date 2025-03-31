package ru.volkovd.music_player.service;

import lombok.RequiredArgsConstructor;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.volkovd.music_player.mapper.TrackMapper;
import ru.volkovd.music_player.model.Album;
import ru.volkovd.music_player.model.Artist;
import ru.volkovd.music_player.model.Track;
import ru.volkovd.music_player.dto.TrackDTO;
import ru.volkovd.music_player.repository.TrackRepository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrackService {

    private final TrackRepository trackRepository;
    private final ArtistService artistService;
    private final AlbumService albumService;

    @Value("${upload.directory}") // Директория для загрузки файлов (настраивается в application.properties)
    private String uploadDirectory;

    public List<TrackDTO> getAllTracks() {
        List<Track> tracks = trackRepository.findAll();
        TrackMapper trackMapper = new TrackMapper();
        return trackMapper.toTrackDTOList(tracks);
    }

    public Track getTrackById(Long id) {
        return trackRepository.findById(id).orElse(null);
    }

    @Transactional
    public Track saveTrack(Track track, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String filePath = saveFile(file); // Сохраняем файл и получаем путь
            track.setSrc(filePath);
        }
        return trackRepository.save(track);
    }

    private org.jaudiotagger.tag.id3.ID3v24Tag convertID3v1ToID3v24(org.jaudiotagger.tag.id3.ID3v1Tag v1Tag) throws FieldDataInvalidException {
        org.jaudiotagger.tag.id3.ID3v24Tag v24Tag = new org.jaudiotagger.tag.id3.ID3v24Tag();
        v24Tag.setEncoding(StandardCharsets.UTF_8); // Устанавливаем UTF-8

        // Копируем поля из v1 в v2.4
        for (FieldKey key : FieldKey.values()) {
            String value = v1Tag.getFirst(key);
            if (value != null && !value.isEmpty()) {
                v24Tag.setField(key, value);
            }
        }
        return v24Tag;
    }

    private String fixEncoding(String text) {
        if (text == null) return null;
        try {
            // Попробуем разные кодировки
            if (text.matches(".*[Ã©Ã¨Ã¢].*")) { // Признак Latin1
                return new String(text.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            } else if (text.matches(".*[Ð°-Ð¿Ñ€-ÑÑ‚-ÑŽ].*")) { // Признак Windows-1251
                return new String(text.getBytes("Windows-1251"), StandardCharsets.UTF_8);
            }
            return text;
        } catch (Exception e) {
            return text;
        }
    }

    @Transactional
    public Track processTrack(MultipartFile multipartFile) throws IOException {
        // Создаем временный файл для MultipartFile
        Path tempFile = Files.createTempFile("temp", ".mp3");
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
        }

        Track track = new Track();

        try {
            AudioFile audioFile = AudioFileIO.read(tempFile.toFile());
            Tag tag = audioFile.getTag();

            if (tag != null) {
                // Читаем данные без изменения кодировки
                String artistName = tag.getFirst(FieldKey.ARTIST);
                String albumName = tag.getFirst(FieldKey.ALBUM);
                String trackTitle = tag.getFirst(FieldKey.TITLE);
                String year = tag.getFirst(FieldKey.YEAR);
                String genre = tag.getFirst(FieldKey.GENRE);

                // Исправляем кодировку
                artistName = fixEncoding(artistName);
                albumName = fixEncoding(albumName);
                trackTitle = fixEncoding(trackTitle);

                // Установка метаданных
                if (trackTitle != null && !trackTitle.isEmpty()) {
                    track.setTitle(trackTitle);
                }
                if (artistName != null && !artistName.isEmpty()) {
                    Artist artistFromDB = artistService.saveArtistIfNotExists(artistName);

                    track.setArtist(artistFromDB);
                    if (albumName != null && !albumName.isEmpty()) {
                        Album album = albumService.saveAlbumIfNotExists(albumName, artistFromDB);
                        track.setAlbum(album);
                    }
                }
            }
        } catch (Exception e) {
            // Обработка ошибок чтения метаданных
            e.printStackTrace();
        } finally {
            // Удаляем временный файл после использования
            Files.deleteIfExists(tempFile);
        }

        ;

        return saveTrack(track, multipartFile);
    }


    public void deleteTrack(Long id) throws IOException {
        Track track = trackRepository.findById(id).orElse(null);
        if (track != null && track.getSrc() != null) {
            deleteFile(track.getSrc()); // Удаляем файл из файловой системы
        }
        trackRepository.deleteById(id);
    }

    private String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("Файл пуст");
        }

        // Создаем директорию, если она не существует
        Path uploadPath = Paths.get(uploadDirectory);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Генерируем уникальное имя файла
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Path.of(fileName);

        // Сохраняем файл
        Files.copy(file.getInputStream(), uploadPath.resolve(filePath));

        return filePath.toString(); // Возвращаем полный путь к файлу
    }

    private void deleteFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            Files.delete(path);
        }
    }
}