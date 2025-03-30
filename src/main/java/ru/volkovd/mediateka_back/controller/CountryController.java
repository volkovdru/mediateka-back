package ru.volkovd.mediateka_back.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.volkovd.mediateka_back.model.Country;
import ru.volkovd.mediateka_back.service.CountryService;

import java.util.List;

@RestController
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

    @PostMapping("/country")
    public Country createCountry(@RequestBody Country country) {
        return countryService.createCountry(country);
    }

    @PostMapping("/countries")
    public ResponseEntity<List<Country>> createCountries(@RequestBody List<Country> countries) {
        List<Country> createdCountries = countryService.createCountries(countries);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCountries);
    }

    @GetMapping("/country/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Long id) {
        return countryService.getCountryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/country/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable Long id, @RequestBody Country countryDetails) {
        return countryService.updateCountry(id, countryDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/country/{id}")
    public ResponseEntity<Object> deleteCountry(@PathVariable Long id) {
        return countryService.deleteCountry(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}