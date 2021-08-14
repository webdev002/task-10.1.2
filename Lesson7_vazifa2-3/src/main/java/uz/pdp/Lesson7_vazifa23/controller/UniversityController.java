package uz.pdp.Lesson7_vazifa23.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.Lesson7_vazifa23.entity.Address;
import uz.pdp.Lesson7_vazifa23.entity.District;
import uz.pdp.Lesson7_vazifa23.entity.University;
import uz.pdp.Lesson7_vazifa23.payload.UniversityDto;
import uz.pdp.Lesson7_vazifa23.repository.AddressRepository;
import uz.pdp.Lesson7_vazifa23.repository.DistrictRepository;
import uz.pdp.Lesson7_vazifa23.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {

    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    AddressRepository addressRepository;

    DistrictRepository districtRepository;

    //Create
    @RequestMapping(value = "/university", method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversityDto universityDto) {
        Address address = new Address();
        Optional<District> district = districtRepository.findById(universityDto.getDistrictId());
        district.ifPresent(address::setDistrict);
        address.setStreet(universityDto.getStreet());
        address.setHouseNumber(universityDto.getHouseNumber());
        Address savedAddress = addressRepository.save(address);

        University university = new University();
        university.setName(universityDto.getName());
        university.setAddress(savedAddress);

        universityRepository.save(university);

        return "Successfully added";
    }

    //Read
    @RequestMapping(value = "/university", method = RequestMethod.GET)
    public List<University> getUniversities() {
        return universityRepository.findAll();
    }

    //Update
    @RequestMapping(value = "/university/{id}", method = RequestMethod.PUT)
    public String editUniversity(@PathVariable Integer id, @RequestBody UniversityDto universityDto) {
        Optional<University> optionalUniversity = universityRepository.findById(id);
        Optional<District> optionalDistrict = districtRepository.findById(universityDto.getDistrictId());
        if (optionalUniversity.isPresent()) {
            University editingUniversity = optionalUniversity.get();
            editingUniversity.setName(universityDto.getName());
            Address address = editingUniversity.getAddress();
            optionalDistrict.ifPresent(address::setDistrict);
            address.setStreet(universityDto.getStreet());
            address.setHouseNumber(universityDto.getHouseNumber());
            addressRepository.save(address);
            universityRepository.save(editingUniversity);

            return "Successfully edited";
        }
        return "University not found";
    }

    // Delete
    @RequestMapping(value = "/university/{id}", method = RequestMethod.DELETE)
    public String deleteStudent(@PathVariable Integer id) {
        universityRepository.deleteById(id);
        boolean deleted = universityRepository.existsById(id);
        if (deleted) {
            return "Successfully deleted";
        } else {
            return "University not found";
        }
    }
}
