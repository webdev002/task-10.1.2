package uz.pdp.Lesson7_vazifa23.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.Lesson7_vazifa23.entity.Faculty;
import uz.pdp.Lesson7_vazifa23.entity.Groups;
import uz.pdp.Lesson7_vazifa23.payload.GroupDto;
import uz.pdp.Lesson7_vazifa23.repository.FacultyRepository;
import uz.pdp.Lesson7_vazifa23.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class GroupController {
    GroupRepository groupsRepository;
    FacultyRepository facultyRepository;

    //Create
    @RequestMapping(value = "/group", method = RequestMethod.POST)
    public String addGroup(@RequestBody GroupDto groupDto) {
        List<Groups> groupsList = groupsRepository.findAll();
        for (Groups searchingSubject : groupsList) {
            if (searchingSubject.getName().equals(groupDto.getName())) {
                return "Group already exist";
            } else {
                Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
                Groups groups = new Groups();
                groups.setName(groupDto.getName());
                optionalFaculty.ifPresent(groups::setFaculty);
                groupsRepository.save(groups);
            }
        }
        return "Group added";
    }

    //Read
    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public List<Groups> getGroups() {
        return groupsRepository.findAll();
    }

    //Update
    @RequestMapping(value = "/group/{id}", method = RequestMethod.PUT)
    public String editGroup(@PathVariable Integer id, @RequestBody GroupDto groupDto) {
        Optional<Groups> optionalGroup = groupsRepository.findById(id);
        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
        if (optionalGroup.isPresent()) {
            Groups editingGroup = optionalGroup.get();
            Faculty faculty = editingGroup.getFaculty();
            optionalFaculty.ifPresent(editingGroup::setFaculty);
            facultyRepository.save(faculty);
            editingGroup.setName(groupDto.getName());
            groupsRepository.save(editingGroup);
            return "Successfully edited";
        }
        return "Group not found";
    }

    // Delete
    @RequestMapping(value = "/group/{id}", method = RequestMethod.DELETE)
    public String deleteGroup(@PathVariable Integer id) {
        groupsRepository.deleteById(id);
        boolean deleted = groupsRepository.existsById(id);
        if (deleted) {
            return "Successfully deleted";
        } else {
            return "Group not found";
        }
    }
}
