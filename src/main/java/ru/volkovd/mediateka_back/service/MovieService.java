package ru.volkovd.mediateka_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.volkovd.mediateka_back.model.Movie;
import ru.volkovd.mediateka_back.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Получить все фильмы
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // Получить фильм по ID
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    // Создать новый фильм
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    // Обновить существующий фильм
    public Optional<Movie> updateMovie(Long id, Movie movieDetails) {
        return movieRepository.findById(id)
                .map(movie -> {
                    movie.setTitle(movieDetails.getTitle());
                    movie.setDirector(movieDetails.getDirector());
                    movie.setYear(movieDetails.getYear());
                    return movieRepository.save(movie);
                });
    }

    // Удалить фильм
    public boolean deleteMovie(Long id) {
        return movieRepository.findById(id)
                .map(movie -> {
                    movieRepository.delete(movie);
                    return true;
                })
                .orElse(false);
    }
}