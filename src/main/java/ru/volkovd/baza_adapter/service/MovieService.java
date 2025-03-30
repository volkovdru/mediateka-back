package ru.volkovd.baza_adapter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.volkovd.baza_adapter.model.MovieData;

@Component
public class MovieService {

    private final MovieServiceClient movieServiceClient;

    @Autowired
    public MovieService(MovieServiceClient movieServiceClient) {
        this.movieServiceClient = movieServiceClient;
    }

    public MovieData fetchAndDisplayMovieDetails(Long movieId) {
        MovieData movieData = movieServiceClient.getMovieDetails(movieId);
        if (movieData != null) {
            System.out.println(movieId + " " + movieData.getName());
        } else {
            System.out.println(movieId + " movie not found!");
        }
        return movieData;
    }
}