package uz.pdp.Lesson7_vazifa23.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.Lesson7_vazifa23.entity.Teacher;
import uz.pdp.Lesson7_vazifa23.repository.TeacherRepository;


import java.util.List;
import java.util.Optional;

@RestController
public class  TeacherController {
    TeacherRepository teacherRepository;
    //Create
    @RequestMapping(value = "/teacher", method = RequestMethod.POST)
    public String addTeacher(Teacher teacher){
        teacherRepository.save(teacher);
        return "Successfully added";
    }

    //Read
    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    //Update
    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.PUT)
    public String editGroup(@PathVariable Integer id, @RequestBody Teacher teacher) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if (optionalTeacher.isPresent()) {
            Teacher editingTeacher = optionalTeacher.get();
            editingTeacher.setFirstName(teacher.getFirstName());
            editingTeacher.setLastName(teacher.getLastName());
            editingTeacher.setAge(teacher.getAge());
            editingTeacher.setSubject(teacher.getSubject());
            editingTeacher.setGroups(teacher.getGroups());
            teacherRepository.save(editingTeacher);
            return "Successfully edited";
        }
        return "Group not found";
    }

    // Delete
    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.DELETE)
    public String deleteTeacher(@PathVariable Integer id) {
        teacherRepository.deleteById(id);
        boolean deleted = teacherRepository.existsById(id);
        if (deleted) {
            return "Successfully deleted";
        } else {
            return "Teacher not found";
        }
    }
}
