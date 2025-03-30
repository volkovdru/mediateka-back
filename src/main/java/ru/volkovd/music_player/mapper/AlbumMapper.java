package ru.volkovd.music_player.mapper;

import org.springframework.stereotype.Component;
import ru.volkovd.music_player.dto.AlbumDTO;
import ru.volkovd.music_player.model.Album;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlbumMapper {

    public List<AlbumDTO> toAlbumDTOList(List<Album> albums) {
        AlbumMapper albumMapper = new AlbumMapper();
        return albumMapper.toAlbumDTOList(albums);
    }

    public AlbumDTO toAlbumDTO(Album album) {
        TrackMapper trackMapper = new TrackMapper();
        return new AlbumDTO(
                album.getId(),
                album.getName(),
                album.getYear(),
                album.getArtist().getName(),
                trackMapper.toTrackDTOList(album.getTracks())
        );
    }
}