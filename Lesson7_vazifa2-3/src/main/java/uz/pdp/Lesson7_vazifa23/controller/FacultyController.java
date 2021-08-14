package uz.pdp.Lesson7_vazifa23.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.Lesson7_vazifa23.entity.Faculty;
import uz.pdp.Lesson7_vazifa23.entity.University;
import uz.pdp.Lesson7_vazifa23.payload.FacultyDto;
import uz.pdp.Lesson7_vazifa23.repository.FacultyRepository;
import uz.pdp.Lesson7_vazifa23.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class FacultyController {
    FacultyRepository facultyRepository;
    UniversityRepository universityRepository;


    //Create
    @RequestMapping(value = "/faculty", method = RequestMethod.POST)
    public String addFaculty(@RequestBody FacultyDto facultyDto) {
        boolean existByName(Srting name);
        List<Faculty> facultyList = facultyRepository.findAll();
        for (Faculty searchingFaculty : facultyList) {
            if (searchingFaculty.getName().equals(facultyDto.getName())) {
                return "Faculty already exist";
            } else {
                Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
                Faculty faculty = new Faculty();
                faculty.setName(facultyDto.getName());
                optionalUniversity.ifPresent(faculty::setUniversity);
                facultyRepository.save(faculty);
            }
        }
        return "Faculty added";
    }

    //Read
    @RequestMapping(value = "/faculty", method = RequestMethod.GET)
    public List<Faculty> getFaculty() {
        return facultyRepository.findAll();
    }

    //Update
    @RequestMapping(value = "/faculty/{id}", method = RequestMethod.PUT)
    public String editFaculty(@PathVariable Integer id, @RequestBody FacultyDto facultyDto) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
        if (optionalFaculty.isPresent()) {
            Faculty editingFaculty = optionalFaculty.get();
            University university = editingFaculty.getUniversity();
            optionalUniversity.ifPresent(editingFaculty::setUniversity);
            universityRepository.save(university);
            editingFaculty.setName(facultyDto.getName());
            facultyRepository.save(editingFaculty);
            return "Successfully edited";
        }
        return "Faculty not found";
    }

    //Delete
    @RequestMapping(value = "/faculty/{id}", method = RequestMethod.DELETE)
    public String deleteSubject(@PathVariable Integer id) {
        facultyRepository.deleteById(id);
        boolean deleted = facultyRepository.existsById(id);
        if (deleted) {
            return "Successfully deleted";
        } else {
            return "Faculty not found";
        }
    }

}
