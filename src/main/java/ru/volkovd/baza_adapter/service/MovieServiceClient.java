package ru.volkovd.baza_adapter.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.volkovd.baza_adapter.model.MovieData;
import ru.volkovd.baza_adapter.model.MovieResponse;

@Service
public class MovieServiceClient {

    private final RestTemplate restTemplate;

    private final String API_URL = "https://api.baza.net/portal/movie/";

    public MovieServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public MovieData getMovieDetails(Long movieId) {
        String url = API_URL + movieId;

        try {
            MovieResponse response = restTemplate.getForObject(url, MovieResponse.class);

            if (response != null && response.getData() != null) {
                return response.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}