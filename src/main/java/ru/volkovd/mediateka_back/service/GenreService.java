package ru.volkovd.mediateka_back.service;

import org.springframework.stereotype.Service;
import ru.volkovd.mediateka_back.model.Genre;
import ru.volkovd.mediateka_back.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Optional<Genre> getGenreById(Long id) {
        return genreRepository.findById(id);
    }

    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public List<Genre> createGenres(List<Genre> genres) {
        return genreRepository.saveAll(genres);
    }

    public Optional<Genre> updateGenre(Long id, Genre genreDetails) {
        return genreRepository.findById(id)
                .map(genre -> {
                    genre.setName(genreDetails.getName());
                    return genreRepository.save(genre);
                });
    }

    public boolean deleteGenre(Long id) {
        return genreRepository.findById(id)
                .map(genre -> {
                    genreRepository.delete(genre);
                    return true;
                })
                .orElse(false);
    }
}