package uz.pdp.Lesson7_vazifa23.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.Lesson7_vazifa23.entity.Continent;
import uz.pdp.Lesson7_vazifa23.entity.Country;
import uz.pdp.Lesson7_vazifa23.payload.CountryDto;
import uz.pdp.Lesson7_vazifa23.repository.ContinentRepository;
import uz.pdp.Lesson7_vazifa23.repository.CountryRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class CountryController {
    CountryRepository countryRepository;
    ContinentRepository continentRepository;

    //Create
    @RequestMapping(value = "/country", method = RequestMethod.POST)
    public String addCountry(@RequestBody CountryDto countryDto) {
        List<Country> countryList = countryRepository.findAll();
        for (Country searchingCountry : countryList) {
            if (searchingCountry.getName().equals(countryDto.getName())) {
                return "Country already exist";
            } else {
                Country country = new Country();
                Optional<Continent> continentById = continentRepository.findById(countryDto.getContinentId());
                country.setName(countryDto.getName());
                continentById.ifPresent(country::setContinent);
                countryRepository.save(country);
            }
        }
        return "Country added";
    }

    //Read
    @RequestMapping(value = "/country", method = RequestMethod.GET)
    public List<Country> getCountry() {
        return countryRepository.findAll();
    }

    //Update
    @RequestMapping(value = "/country/{id}", method = RequestMethod.PUT)
    public String editCountry(@PathVariable Integer id, @RequestBody CountryDto countryDto) {
        Optional<Country> optionalCountry = countryRepository.findById(id);
        Optional<Continent> optionalContinent = continentRepository.findById(countryDto.getContinentId());
        if (optionalCountry.isPresent()) {
            Country editingCountry = optionalCountry.get();
            Continent continent = editingCountry.getContinent();
            optionalContinent.ifPresent(editingCountry::setContinent);
            continentRepository.save(continent);
            editingCountry.setName(countryDto.getName());
            countryRepository.save(editingCountry);
            return "Successfully edited";
        }
        return "Country not found";
    }

    //Delete
    @RequestMapping(value = "/country/{id}", method = RequestMethod.DELETE)
    public String deleteCountry(@PathVariable Integer id) {
        countryRepository.deleteById(id);
        boolean deleted = countryRepository.existsById(id);
        if (deleted) {
            return "Successfully deleted";
        } else {
            return "Country not found";
        }
    }
}
