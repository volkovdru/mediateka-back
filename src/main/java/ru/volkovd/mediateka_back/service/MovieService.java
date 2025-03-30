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

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Optional<Movie> updateMovie(Long id, Movie movieDetails) {
        return movieRepository.findById(id)
                .map(movie -> {
                    movie.setIdBz(movieDetails.getIdBz());
                    movie.setName(movieDetails.getName());
                    movie.setInternationalName(movieDetails.getInternationalName());
                    movie.setDescription(movieDetails.getDescription());
                    movie.setYear(movieDetails.getYear());
                    movie.setGenres(movieDetails.getGenres());
                    movie.setCountries(movieDetails.getCountries());
                    movie.setRatingBz(movieDetails.getRatingBz());
                    movie.setRatingKp(movieDetails.getRatingKp());
                    movie.setPosters(movieDetails.getPosters());
                    return movieRepository.save(movie);
                });
    }

    public boolean deleteMovie(Long id) {
        return movieRepository.findById(id)
                .map(movie -> {
                    movieRepository.delete(movie);
                    return true;
                })
                .orElse(false);
    }
}