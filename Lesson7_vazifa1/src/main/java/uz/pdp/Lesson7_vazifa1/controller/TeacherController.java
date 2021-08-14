package uz.pdp.Lesson7_vazifa1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.Lesson7_vazifa1.entity.School;
import uz.pdp.Lesson7_vazifa1.entity.Subject;
import uz.pdp.Lesson7_vazifa1.entity.Teacher;
import uz.pdp.Lesson7_vazifa1.payload.TeacherDto;
import uz.pdp.Lesson7_vazifa1.repository.SchoolRepository;
import uz.pdp.Lesson7_vazifa1.repository.SubjectRepository;
import uz.pdp.Lesson7_vazifa1.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class TeacherController {

    @Autowired
    SchoolRepository schoolRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    SubjectRepository subjectRepository;



    //GET Teacher
    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    public List<Teacher> getTeacher(){
        return  teacherRepository.findAll();
    }


    //ADDED Teacher
    @RequestMapping(value = "/teacher", method = RequestMethod.POST)
    public String addTeacher(@RequestBody TeacherDto teacherDto){
        Optional<School> optionalSchool = schoolRepository.findById(teacherDto.getSchoolId());
        if (optionalSchool.isPresent()) {
            Optional<Subject> optionalSubject = subjectRepository.findById(teacherDto.getSubjectId());
            if (optionalSubject.isPresent()) {
                Teacher newTeacher=new Teacher();
                newTeacher.setFirstName(teacherDto.getFirstName());
                newTeacher.setLastName(teacherDto.getLastName());
                newTeacher.setAge(teacherDto.getAge());
                newTeacher.setSalary(teacherDto.getSalary());
                newTeacher.setSpecialty(optionalSubject.get());
                newTeacher.setWorkplace(optionalSchool.get());
                teacherRepository.save(newTeacher);
                return "Teacher added";
            }else {
                return "The Specialty you entered is not available in the database." +
                        " If you want to add this teacher and then the Specialty will also be new," +
                        " first add a new Specialty to the Subject schedule, then continue again ...";
            }
        }else {
            return "The school you entered is not available in the database." +
                    " If you want to add this teacher and then the school will also be new," +
                    " first add a new school to the school schedule, then continue again ...";
        }
    }

    //GET Teacher BY ID
    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.GET)
    public Teacher getTeacherId(@PathVariable Integer id){
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        return optionalTeacher.orElseGet(Teacher::new);
    }

    //DELETE Teacher
    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.DELETE)
    public String deleteTeacher(@PathVariable Integer id){
        List<Teacher> teacherList = teacherRepository.findAll();
        for (Teacher teacher : teacherList) {
            if (teacher.getId().equals(id)){
                teacherRepository.deleteById(id);
                return "Teacher deleted";
            }
        }
        return "Teacher not found";
    }

    //UPDATE Teacher
    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.PUT)
    public String updateTeacher(@PathVariable Integer id, @RequestBody TeacherDto teacherDto){
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if (optionalTeacher.isPresent()){
            Optional<School> optionalSchool = schoolRepository.findById(teacherDto.getSchoolId());
            if (optionalSchool.isPresent()) {
                Optional<Subject> optionalSubject = subjectRepository.findById(teacherDto.getSubjectId());
                if (optionalSubject.isPresent()) {
                    Teacher editedTeacher=optionalTeacher.get();
                    editedTeacher.setFirstName(teacherDto.getFirstName());
                    editedTeacher.setLastName(teacherDto.getLastName());
                    editedTeacher.setAge(teacherDto.getAge());
                    editedTeacher.setSalary(teacherDto.getSalary());
                    editedTeacher.setSpecialty(optionalSubject.get());
                    editedTeacher.setWorkplace(optionalSchool.get());
                    teacherRepository.save(editedTeacher);
                    return "Teacher edited";
                }else {
                    return "The Specialty you entered is not available in the database." +
                            " If you want to edit this teacher and then the Specialty will also be new," +
                            " first add a new Specialty to the Subject schedule, then continue again ...";
                }
            }else {
                return "The school you entered is not available in the database." +
                        " If you want to edit this teacher and then the school will also be new," +
                        " first add a new school to the school schedule, then continue again ...";
            }
        }else {
            return "Teacher not found";
        }
    }
}
