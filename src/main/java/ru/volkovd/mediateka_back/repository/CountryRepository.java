package ru.volkovd.mediateka_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.volkovd.mediateka_back.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}