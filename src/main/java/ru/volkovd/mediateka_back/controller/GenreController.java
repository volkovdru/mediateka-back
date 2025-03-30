package ru.volkovd.mediateka_back.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.volkovd.mediateka_back.model.Genre;
import ru.volkovd.mediateka_back.service.GenreService;

import java.util.List;

@RestController
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genres")
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    @PostMapping("/genre")
    public Genre createGenre(@RequestBody Genre genre) {
        return genreService.createGenre(genre);
    }

    @PostMapping("/genres")
    public ResponseEntity<List<Genre>> createGenres(@RequestBody List<Genre> genres) {
        List<Genre> createdGenres = genreService.createGenres(genres);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGenres);
    }

    @GetMapping("/genre/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Long id) {
        return genreService.getGenreById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/genre/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long id, @RequestBody Genre genreDetails) {
        return genreService.updateGenre(id, genreDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/genre/{id}")
    public ResponseEntity<Object> deleteGenre(@PathVariable Long id) {
        return genreService.deleteGenre(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}