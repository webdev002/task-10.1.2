package uz.pdp.Lesson7_vazifa1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.Lesson7_vazifa1.entity.Subject;
import uz.pdp.Lesson7_vazifa1.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class SubjectController {
    @Autowired
    SubjectRepository subjectRepository;


    //GET Subject
    @RequestMapping(value = "/subject", method = RequestMethod.GET)
    public List<Subject> getSubject(){
        return  subjectRepository.findAll();
    }


    //ADDED Subject
    @RequestMapping(value = "/subject", method = RequestMethod.POST)
    public String addSubject(@RequestBody Subject subject){
        // subjectni id orqali olishnig iloji yo'q chunki RequestBody da id kelmaydi
        List<Subject> subjectList = subjectRepository.findAll();
        for (Subject selectedSubject : subjectList) {
            if (selectedSubject.getSubjectName().equals(subject.getSubjectName())) {
                return "Subject already exist";
            }
        }
        Subject newSubject=new Subject();
        newSubject.setSubjectName(subject.getSubjectName());
        subjectRepository.save(newSubject);
        return "Subject added";
    }

    //GET Subject BY ID
    @RequestMapping(value = "/subject/{id}", method = RequestMethod.GET)
    public Subject getSubjectId(@PathVariable Integer id){
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        return optionalSubject.orElseGet(Subject::new);
    }

    //DELETE Subject
    @RequestMapping(value = "/subject/{id}", method = RequestMethod.DELETE)
    public String deleteSubject(@PathVariable Integer id){
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()) {
            subjectRepository.deleteById(id);
            return "Subject deleted";
        }else {
            return "Subject not found";
        }
    }

    //UPDATE Subject
    @RequestMapping(value = "/subject/{id}", method = RequestMethod.PUT)
    public String updateSubject(@PathVariable Integer id, @RequestBody Subject subject){
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()) {
            List<Subject> subjectList = subjectRepository.findAll();
            for (Subject selectedSubject : subjectList) {
                if (selectedSubject.getSubjectName().equals(subject.getSubjectName())){
                    return "Subject already exist, enter new subjectName";
                }
            }
            Subject editedSubject = optionalSubject.get();
            editedSubject.setSubjectName(subject.getSubjectName());
            subjectRepository.save(editedSubject);
            return "Subject edited";
        }else {
            return "Subject not found";
        }
    }
}
