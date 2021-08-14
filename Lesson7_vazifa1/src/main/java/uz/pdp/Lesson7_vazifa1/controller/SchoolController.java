package uz.pdp.Lesson7_vazifa1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.Lesson7_vazifa1.entity.Address;
import uz.pdp.Lesson7_vazifa1.entity.School;
import uz.pdp.Lesson7_vazifa1.payload.SchoolDto;
import uz.pdp.Lesson7_vazifa1.repository.AddressRepository;
import uz.pdp.Lesson7_vazifa1.repository.SchoolRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class SchoolController {
    @Autowired
    SchoolRepository schoolRepository;
    @Autowired
    AddressRepository addressRepository;


    //GET School
    @RequestMapping(value = "/school", method = RequestMethod.GET)
    public List<School> getSchool(){
        return  schoolRepository.findAll();
    }


    //ADDED School
    @RequestMapping(value = "/school", method = RequestMethod.POST)
    public String addSchool(@RequestBody SchoolDto schoolDto){
        List<School> schoolList = schoolRepository.findAll();
        for (School school : schoolList) {
            if (school.getName().equals(schoolDto.getSchoolName())){
                return "School  already exist";
            }
        }
        Address newAddress=new Address();
        newAddress.setCountry(schoolDto.getCountry());
        newAddress.setRegion(schoolDto.getRegion());
        newAddress.setDistrict(schoolDto.getDistrict());
        newAddress.setStreet(schoolDto.getStreet());
        newAddress.setHome(schoolDto.getHome());
        Address savedAddress = addressRepository.save(newAddress);
        School school=new School();
        school.setName(schoolDto.getSchoolName());
        school.setAddress(savedAddress);
        schoolRepository.save(school);
        return "School added";
    }

    //GET School BY ID
    @RequestMapping(value = "/school/{id}", method = RequestMethod.GET)
    public School getSchoolId(@PathVariable Integer id){
        Optional<School> optionalSchool = schoolRepository.findById(id);
        return optionalSchool.orElseGet(School::new);
    }

    //DELETE School
    @RequestMapping(value = "/school/{id}", method = RequestMethod.DELETE)
    public String deleteSchool(@PathVariable Integer id){
        List<School> schools = schoolRepository.findAll();
        for (School school : schools) {
            if (school.getId().equals(id)){
                schoolRepository.deleteById(id);
                return "School deleted";
            }
        }
        return "School not found";
    }

    //UPDATE School
    @RequestMapping(value = "/school/{id}", method = RequestMethod.PUT)
    public String updateSchool(@PathVariable Integer id, @RequestBody SchoolDto schoolDto){
        Optional<School> optionalSchool = schoolRepository.findById(id);
        List<School> schoolList = schoolRepository.findAll();
        for (School school : schoolList) {
            if (school.getName().equals(schoolDto.getSchoolName())){
                return "School  already exist";
            }
        }
        if (optionalSchool.isPresent()){
            School editedSchool = optionalSchool.get();
            Address schoolAddress = editedSchool.getAddress();
            schoolAddress.setCountry(schoolDto.getCountry());
            schoolAddress.setRegion(schoolDto.getRegion());
            schoolAddress.setDistrict(schoolDto.getDistrict());
            schoolAddress.setStreet(schoolDto.getStreet());
            schoolAddress.setHome(schoolDto.getHome());
            Address savedAddress = addressRepository.save(schoolAddress);
            editedSchool.setName(schoolDto.getSchoolName());
            editedSchool.setAddress(savedAddress);
            schoolRepository.save(editedSchool);
            return "School edited";
        }else {
            return "School not found";
        }
    }
}
