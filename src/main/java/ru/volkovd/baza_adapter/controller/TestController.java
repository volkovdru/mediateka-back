package ru.volkovd.baza_adapter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.volkovd.baza_adapter.exception.MovieNotFoundException;
import ru.volkovd.baza_adapter.model.MovieData;
import ru.volkovd.baza_adapter.service.MovieService;
import java.io.PrintWriter;
import java.io.StringWriter;

@RestController
@RequestMapping("/test")
public class TestController {

    private final MovieService movieService;

    public TestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<MovieData> getMovie(@PathVariable Long id) {
        MovieData movieData = movieService.fetchAndDisplayMovieDetails(id);
        if (movieData != null) {
            return ResponseEntity.ok(movieData); // Возвращаем данные с кодом 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Возвращаем 404, если фильм не найден
        }
    }

    @GetMapping("/movies")
    public ResponseEntity<MovieData> getMovies() {
        MovieData movieData = null;
        for (Long i = 1L; i < 1000; i++) {
            movieData = movieService.fetchAndDisplayMovieDetails(i);
        }
        return ResponseEntity.ok(movieData);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<String> handleMovieNotFound(MovieNotFoundException ex) {

        // Преобразуем стектрейс в строку
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw); // Записываем стектрейс в PrintWriter
        String stackTrace = sw.toString();

        // Формируем тело ответа с сообщением и стектрейсом
        String responseBody = "Ошибка: " + ex.getMessage() + "\n\nСтектрейс:\n" + stackTrace;

        // Возвращаем ответ с кодом 500 и телом, содержащим стектрейс
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }
}