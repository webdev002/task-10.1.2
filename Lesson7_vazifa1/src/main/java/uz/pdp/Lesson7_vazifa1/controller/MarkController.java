package uz.pdp.Lesson7_vazifa1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.Lesson7_vazifa1.entity.*;
import uz.pdp.Lesson7_vazifa1.payload.MarkDto;
import uz.pdp.Lesson7_vazifa1.payload.StudentDto;
import uz.pdp.Lesson7_vazifa1.repository.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MarkController {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    MarkRepository markRepository;



    //GET Mark
    @RequestMapping(value = "/mark", method = RequestMethod.GET)
    public List<Mark> getMark(){
        return  markRepository.findAll();
    }


    //ADDED Mark
    @RequestMapping(value = "/mark", method = RequestMethod.POST)
    public String addMark(@RequestBody MarkDto markDto){
        Optional<Student> optionalStudent = studentRepository.findById(markDto.getStudentId());
        if (optionalStudent.isPresent()) {
            Optional<Teacher> optionalTeacher = teacherRepository.findById(markDto.getTeacherId());
            if (optionalTeacher.isPresent()) {
                Optional<Subject> optionalSubject = subjectRepository.findById(markDto.getSubjectId());
                if (optionalSubject.isPresent()) {
                    Mark newMark =new Mark();
                    newMark.setQuantityOfMark(markDto.getQuantityOfMark());
                    newMark.setDate(markDto.getDate());
                    newMark.setStudent(optionalStudent.get());
                    newMark.setSubject(optionalSubject.get());
                    newMark.setTeacher(optionalTeacher.get());
                    markRepository.save(newMark);
                    return "Mark added";
                }else {
                    return "The subject you entered is not available in the database." +
                            " If you want to add this Mark and then the subject will also be new," +
                            " first add a new subject to the subject schedule, then continue again ...";
                }

            }else {
                return "The teacher you entered is not available in the database." +
                        " If you want to add this Mark and then the teacher will also be new," +
                        " first add a new teacher to the teacher schedule, then continue again ...";
            }

        } else {
            return "The student you entered is not available in the database." +
                    " If you want to add this Mark  and then the student will also be new," +
                    " first add a new student to the Student schedule, then continue again ...";
        }
    }

    //GET Mark BY ID
    @RequestMapping(value = "/mark/{id}", method = RequestMethod.GET)
    public Mark getMarkById(@PathVariable Integer id){
        Optional<Mark> optionalMark = markRepository.findById(id);
        return optionalMark.orElseGet(Mark::new);
    }

    //DELETE Mark
    @RequestMapping(value = "/mark/{id}", method = RequestMethod.DELETE)
    public String deleteMark(@PathVariable Integer id){
        List<Mark> markList = markRepository.findAll();
        for (Mark mark : markList) {
            if (mark.getId().equals(id)){
                markRepository.deleteById(id);
                return "Mark deleted";
            }
        }
        return "Mark not found";
    }

    //UPDATE Mark
    @RequestMapping(value = "/mark/{id}", method = RequestMethod.PUT)
    public String updateMark(@PathVariable Integer id, @RequestBody MarkDto markDto) {
        Optional<Mark> optionalMark = markRepository.findById(id);
        if (optionalMark.isPresent()) {
            Optional<Student> optionalStudent = studentRepository.findById(markDto.getStudentId());
            if (optionalStudent.isPresent()) {
                Optional<Teacher> optionalTeacher = teacherRepository.findById(markDto.getTeacherId());
                if (optionalTeacher.isPresent()) {
                    Optional<Subject> optionalSubject = subjectRepository.findById(markDto.getSubjectId());
                    if (optionalSubject.isPresent()) {
                        Mark editedMark = optionalMark.get();
                        editedMark.setQuantityOfMark(markDto.getQuantityOfMark());
                        editedMark.setDate(markDto.getDate());
                        editedMark.setStudent(optionalStudent.get());
                        editedMark.setSubject(optionalSubject.get());
                        editedMark.setTeacher(optionalTeacher.get());
                        markRepository.save(editedMark);
                        return "Mark edited";
                    }else {
                        return "The subject you entered is not available in the database." +
                                " If you want to edit this Mark and then the subject will also be new," +
                                " first add a new subject to the subject schedule, then continue again ...";
                    }

                }else {
                    return "The teacher you entered is not available in the database." +
                            " If you want to edit this Mark and then the teacher will also be new," +
                            " first add a new teacher to the teacher schedule, then continue again ...";
                }

            } else {
                return "The student you entered is not available in the database." +
                        " If you want to edit this Mark  and then the student will also be new," +
                        " first add a new student to the Student schedule, then continue again ...";
            }
        } else {
            return "Mark not found";
        }
    }
}
