package ru.volkovd.mediateka_back.service;

import jakarta.transaction.Transactional;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import ru.volkovd.mediateka_back.model.Country;
import ru.volkovd.mediateka_back.repository.CountryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Optional<Country> getCountryById(Long id) {
        return countryRepository.findById(id);
    }

    public Country createCountry(Country country) {
        return countryRepository.save(country);
    }

    public List<Country> createCountries(List<Country> countries) {
        try {
            return countryRepository.saveAll(countries);
        } catch (ObjectOptimisticLockingFailureException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Конфликт версий. Данные были изменены другим пользователем.");
        }
    }

    public Optional<Country> updateCountry(Long id, Country countryDetails) {
        return countryRepository.findById(id)
                .map(country -> {
                    country.setName(countryDetails.getName());
                    return countryRepository.save(country);
                });
    }

    public boolean deleteCountry(Long id) {
        return countryRepository.findById(id)
                .map(country -> {
                    countryRepository.delete(country);
                    return true;
                })
                .orElse(false);
    }
}