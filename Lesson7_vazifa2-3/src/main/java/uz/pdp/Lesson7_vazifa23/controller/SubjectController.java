package uz.pdp.Lesson7_vazifa23.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.Lesson7_vazifa23.entity.Subject;
import uz.pdp.Lesson7_vazifa23.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class SubjectController {
    SubjectRepository subjectRepository;

    //Create
    @RequestMapping(value = "/subject", method = RequestMethod.POST)
    public String addSubject(@RequestBody Subject subject) {
        List<Subject> subjectList = subjectRepository.findAll();
        for (Subject searchingSubject : subjectList) {
            if (searchingSubject.getName().equals(subject.getName())) {
                return "Subject already exist";
            } else {
                subjectRepository.save(subject);
            }
        }
        return "Subject added";
    }

    //Read
    @RequestMapping(value = "/subject", method = RequestMethod.GET)
    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    //Update
    @RequestMapping(value = "/subject/{id}", method = RequestMethod.PUT)
    public String editSubject(@PathVariable Integer id, @RequestBody Subject subject) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()) {
            Subject editingSubject = optionalSubject.get();
            editingSubject.setName(subject.getName());
            subjectRepository.save(editingSubject);
            return "Successfully edited";
        }
        return "Subject not found";
    }

    //Delete
    @RequestMapping(value = "/subject/{id}", method = RequestMethod.DELETE)
    public String deleteSubject(@PathVariable Integer id) {
        subjectRepository.deleteById(id);
        boolean deleted = subjectRepository.existsById(id);
        if (deleted) {
            return "Successfully deleted";
        } else {
            return "Subject not found";
        }
    }
}
