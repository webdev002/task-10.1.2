package uz.pdp.Lesson7_vazifa23.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.Lesson7_vazifa23.entity.Groups;
import uz.pdp.Lesson7_vazifa23.entity.Student;
import uz.pdp.Lesson7_vazifa23.entity.Subject;
import uz.pdp.Lesson7_vazifa23.payload.StudentDto;
import uz.pdp.Lesson7_vazifa23.repository.GroupRepository;
import uz.pdp.Lesson7_vazifa23.repository.StudentRepository;
import uz.pdp.Lesson7_vazifa23.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    StudentRepository studentRepository;
    GroupRepository groupRepository;
    SubjectRepository subjectRepository;

    //Create
    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String addStudent(StudentDto studentDto) {
        Optional<Groups> optionalGroups = groupRepository.findById(studentDto.getGroupId());
        List<Subject> subjectList = subjectRepository.findAllById(studentDto.getSubjectsId());
        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setAge(studentDto.getAge());
        optionalGroups.ifPresent(student::setGroup);
        student.setSubjects(subjectList.subList(0, subjectList.size()));
        studentRepository.save(student);
        return "Successfully added";
    }

    //Read
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    //Update
    @RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
    public String editStudent(@PathVariable Integer id, @RequestBody StudentDto studentDto) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        Optional<Groups> optionalGroups = groupRepository.findById(studentDto.getGroupId());
        List<Subject> subjectList = subjectRepository.findAllById(studentDto.getSubjectsId());

        if (optionalStudent.isPresent()) {
            Student editingStudent = optionalStudent.get();
            Groups groups = editingStudent.getGroup();
            List<Subject> subject = editingStudent.getSubjects();
            editingStudent.setFirstName(studentDto.getFirstName());
            editingStudent.setLastName(studentDto.getLastName());
            editingStudent.setAge(studentDto.getAge());
            optionalGroups.ifPresent(editingStudent::setGroup);
            editingStudent.setSubjects(subjectList.subList(0, subjectList.size()));
            groupRepository.save(groups);
            subjectRepository.save(subject.listIterator().next());
            studentRepository.save(editingStudent);
            return "Successfully edited";
        }
        return "Student not found";
    }

    // Delete
    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public String deleteStudent(@PathVariable Integer id) {
        studentRepository.deleteById(id);
        boolean deleted = studentRepository.existsById(id);
        if (deleted) {
            return "Successfully deleted";
        } else {
            return "Student not found";
        }
    }
}
