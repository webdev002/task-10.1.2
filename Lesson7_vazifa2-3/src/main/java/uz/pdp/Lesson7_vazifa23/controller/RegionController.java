package uz.pdp.Lesson7_vazifa23.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.Lesson7_vazifa23.entity.Country;
import uz.pdp.Lesson7_vazifa23.entity.Region;
import uz.pdp.Lesson7_vazifa23.payload.RegionDto;
import uz.pdp.Lesson7_vazifa23.repository.CountryRepository;
import uz.pdp.Lesson7_vazifa23.repository.RegionRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class RegionController {
    RegionRepository regionRepository;
    CountryRepository countryRepository;

    //Create
    @RequestMapping(value = "/region", method = RequestMethod.POST)
    public String addRegion(@RequestBody RegionDto regionDto) {
        List<Region> regionList = regionRepository.findAll();
        for (Region searchingRegion : regionList) {
            if (searchingRegion.getName().equals(regionDto.getName())) {
                return "Region already exist";
            } else {
                Optional<Country> optionalCountry = countryRepository.findById(regionDto.getCountryId());
                Region region = new Region();
                region.setName(regionDto.getName());
                optionalCountry.ifPresent(region::setCountry);
                regionRepository.save(region);
            }
        }
        return "Region added";
    }

    //Read
    @RequestMapping(value = "/region", method = RequestMethod.GET)
    public List<Region> getRegion() {
        return regionRepository.findAll();
    }

    //Update
    @RequestMapping(value = "/region/{id}", method = RequestMethod.PUT)
    public String editRegion(@PathVariable Integer id, @RequestBody RegionDto regionDto) {
        Optional<Region> optionalRegion = regionRepository.findById(id);
        Optional<Country> optionalCountry = countryRepository.findById(regionDto.getCountryId());
        if (optionalRegion.isPresent()) {
            Region editingRegion = optionalRegion.get();
            Country country = editingRegion.getCountry();
            optionalCountry.ifPresent(editingRegion::setCountry);
            countryRepository.save(country);
            editingRegion.setName(regionDto.getName());
            regionRepository.save(editingRegion);
            return "Successfully edited";
        }
        return "Region not found";
    }

    //Delete
    @RequestMapping(value = "/region/{id}", method = RequestMethod.DELETE)
    public String deleteRegion(@PathVariable Integer id) {
        regionRepository.deleteById(id);
        boolean deleted = regionRepository.existsById(id);
        if (deleted) {
            return "Successfully deleted";
        } else {
            return "Region not found";
        }
    }
}
