package uz.pdp.Lesson7_vazifa23.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.Lesson7_vazifa23.entity.District;
import uz.pdp.Lesson7_vazifa23.entity.Region;
import uz.pdp.Lesson7_vazifa23.payload.DistrictDto;
import uz.pdp.Lesson7_vazifa23.repository.DistrictRepository;
import uz.pdp.Lesson7_vazifa23.repository.RegionRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class DistrictController {
    DistrictRepository districtRepository;
    RegionRepository regionRepository;

    //Create
    @RequestMapping(value = "/district", method = RequestMethod.POST)
    public String addDistrict(@RequestBody DistrictDto districtDto) {
        List<District> districtList = districtRepository.findAll();
        for (District searchingDistrict : districtList) {
            if (searchingDistrict.getName().equals(districtDto.getName())) {
                return "District already exist";
            } else {
                Optional<Region> optionalRegion = regionRepository.findById(districtDto.getRegionId());
                District district = new District();
                district.setName(districtDto.getName());
                optionalRegion.ifPresent(district::setRegion);
                districtRepository.save(district);
            }
        }
        return "District added";
    }

    //Read
    @RequestMapping(value = "/district", method = RequestMethod.GET)
    public List<District> getDistrict() {
        return districtRepository.findAll();
    }

    //Update
    @RequestMapping(value = "/district/{id}", method = RequestMethod.PUT)
    public String editDistrict(@PathVariable Integer id, @RequestBody DistrictDto districtDto) {
        Optional<District> optionalDistrict = districtRepository.findById(id);
        Optional<Region> optionalRegion = regionRepository.findById(districtDto.getRegionId());
        if (optionalDistrict.isPresent()) {
            District editingDistrict = optionalDistrict.get();
            Region region = editingDistrict.getRegion();
            optionalRegion.ifPresent(editingDistrict::setRegion);
            regionRepository.save(region);
            editingDistrict.setName(districtDto.getName());
            districtRepository.save(editingDistrict);
            return "Successfully edited";
        }
        return "District not found";
    }

    //Delete
    @RequestMapping(value = "/district/{id}", method = RequestMethod.DELETE)
    public String deleteDistrict(@PathVariable Integer id) {
        districtRepository.deleteById(id);
        boolean deleted = districtRepository.existsById(id);
        if (deleted) {
            return "Successfully deleted";
        } else {
            return "District not found";
        }
    }
}
