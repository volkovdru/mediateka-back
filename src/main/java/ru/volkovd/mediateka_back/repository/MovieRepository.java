package ru.volkovd.mediateka_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.volkovd.mediateka_back.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}