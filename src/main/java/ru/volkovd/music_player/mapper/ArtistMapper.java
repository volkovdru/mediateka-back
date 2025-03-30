package ru.volkovd.music_player.mapper;

import ru.volkovd.music_player.dto.ArtistDTO;
import ru.volkovd.music_player.dto.TrackDTO;
import ru.volkovd.music_player.model.Artist;
import ru.volkovd.music_player.model.Track;

import java.util.List;
import java.util.stream.Collectors;

public class ArtistMapper {

    public static List<ArtistDTO> toArtistDTOList(List<Artist> artists) {
        return artists.stream()
                .map(ArtistMapper::toArtistDTO)
                .collect(Collectors.toList());
    }

    public static ArtistDTO toArtistDTO(Artist artist) {
        AlbumMapper albumMapper = new AlbumMapper();
        return new ArtistDTO(
                artist.getId(),
                artist.getName(),
                albumMapper.toAlbumDTOList(artist.getAlbums()));
    }
}