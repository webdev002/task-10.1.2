package uz.pdp.Lesson7_vazifa1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.Lesson7_vazifa1.entity.Groups;
import uz.pdp.Lesson7_vazifa1.entity.School;
import uz.pdp.Lesson7_vazifa1.payload.GroupsDto;
import uz.pdp.Lesson7_vazifa1.repository.GroupsRepository;
import uz.pdp.Lesson7_vazifa1.repository.SchoolRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class GroupsController {

    @Autowired
    SchoolRepository schoolRepository;
    @Autowired
    GroupsRepository groupsRepository;



    //GET groups
    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public List<Groups> getGroups(){
        return  groupsRepository.findAll();
    }


    //ADDED Groups
    @RequestMapping(value = "/groups", method = RequestMethod.POST)
    public String addGroups(@RequestBody GroupsDto groupsDto){
        Optional<School> optionalSchool = schoolRepository.findById(groupsDto.getSchoolid());
        if (optionalSchool.isPresent()) {
            Groups groups=new Groups();
            groups.setName(groupsDto.getGroupName());
            groups.setSchool(optionalSchool.get());
            groups.setStudentsNumber(groupsDto.getStudentsNumber());
            groupsRepository.save(groups);
            return "Groups added";
        }else {
            return "The school you entered is not available in the database." +
                    " If you want to add a group and then the school will also be new," +
                    " first add a new school to the school schedule, then continue again ...";
        }
    }

    //GET Groups BY ID
    @RequestMapping(value = "/groups/{id}", method = RequestMethod.GET)
    public Groups getGroupsId(@PathVariable Integer id){
        Optional<Groups> optionalGroups = groupsRepository.findById(id);
        return optionalGroups.orElseGet(Groups::new);
    }

    //DELETE Groups
    @RequestMapping(value = "/groups/{id}", method = RequestMethod.DELETE)
    public String deleteGroups(@PathVariable Integer id){
        List<Groups> groups = groupsRepository.findAll();
        for (Groups selectedgroup : groups) {
            if (selectedgroup.getId().equals(id)){
                groupsRepository.deleteById(id);
                return "Groups deleted";
            }
        }
        return "Groups not found";
    }

    //UPDATE Groups
    @RequestMapping(value = "/groups/{id}", method = RequestMethod.PUT)
    public String updateGroups(@PathVariable Integer id, @RequestBody GroupsDto groupsDto){
        Optional<Groups> optionalGroups = groupsRepository.findById(id);
        if (optionalGroups.isPresent()){
            Optional<School> optionalSchool = schoolRepository.findById(groupsDto.getSchoolid());
            if (optionalSchool.isPresent()) {
                Groups editedGroup = optionalGroups.get();
                editedGroup.setName(groupsDto.getGroupName());
                editedGroup.setStudentsNumber(groupsDto.getStudentsNumber());
                editedGroup.setSchool(optionalSchool.get());
                groupsRepository.save(editedGroup);
                return "Groups edited";
            }else {
                return "The school you entered is not available in the database." +
                        " If you want to edit this group and then the school will also be new," +
                        " first add a new school to the school schedule, then continue again ...";
            }
        }else {
            return "Groups not found";
        }
    }
}
