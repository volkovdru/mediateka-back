package ru.volkovd.music_player.mapper;

import org.springframework.stereotype.Component;
import ru.volkovd.music_player.model.Track;
import ru.volkovd.music_player.dto.TrackDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrackMapper {

    public TrackDTO toTrackDTO(Track track) {
        if (track == null) {
            return null;
        }

        return new TrackDTO(
                track.getId(),
                track.getTitle(),
                track.getAlbum() != null ? track.getAlbum().getName() : null,
                track.getArtist() != null ? track.getArtist().getName() : null,
                track.getSrc()
        );
    }

    public List<TrackDTO> toTrackDTOList(List<Track> tracks) {
        return tracks.stream()
                .map(this::toTrackDTO)
                .collect(Collectors.toList());
    }
}