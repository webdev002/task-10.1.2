package uz.pdp.Lesson7_vazifa1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.Lesson7_vazifa1.entity.Groups;
import uz.pdp.Lesson7_vazifa1.entity.Student;
import uz.pdp.Lesson7_vazifa1.payload.StudentDto;
import uz.pdp.Lesson7_vazifa1.repository.GroupsRepository;
import uz.pdp.Lesson7_vazifa1.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GroupsRepository groupsRepository;



    //GET Student
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public List<Student> getStudent(){
        return  studentRepository.findAll();
    }


    //ADDED Student
    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String addStudent(@RequestBody StudentDto studentDto){
        Optional<Groups> optionalGroups = groupsRepository.findById(studentDto.getGroupId());
        if (optionalGroups.isPresent()) {
            Student newStudent=new Student();
            newStudent.setFirstName(studentDto.getFirstName());
            newStudent.setLastName(studentDto.getLastName());
            newStudent.setAge(studentDto.getAge());
            newStudent.setGroups(optionalGroups.get());
            studentRepository.save(newStudent);
            return "Student added";
        }else {
            return "The groups you entered is not available in the database." +
                    " If you want to add a Student and then the groups will also be new," +
                    " first add a new groups to the groups schedule, then continue again ...";
        }
    }

    //GET Student BY ID
    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public Student getStudentById(@PathVariable Integer id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        return optionalStudent.orElseGet(Student::new);
    }

    //DELETE Student
    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public String deleteStudent(@PathVariable Integer id){
        List<Student> studentList = studentRepository.findAll();
        for (Student student : studentList) {
            if (student.getId().equals(id)){
                studentRepository.deleteById(id);
                return "Student deleted";
            }
        }
        return "Student not found";
    }

    //UPDATE Student
    @RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
    public String updateStudent(@PathVariable Integer id, @RequestBody StudentDto studentDto){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()){
            Optional<Groups> optionalGroups = groupsRepository.findById(studentDto.getGroupId());
            if (optionalGroups.isPresent()){
                Student editedStudent = optionalStudent.get();
                editedStudent.setFirstName(studentDto.getFirstName());
                editedStudent.setLastName(studentDto.getLastName());
                editedStudent.setAge(studentDto.getAge());
                editedStudent.setGroups(optionalGroups.get());
                studentRepository.save(editedStudent);
                return "Student edited";
            }else {
                return "The Group you entered is not available in the database." +
                        " If you want to edit this Student and then the Group will also be new," +
                        " first add a new Group to the Groups schedule, then continue again ...";
            }
        }else {
            return "Student not found";
        }
    }
}
