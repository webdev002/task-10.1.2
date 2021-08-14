package uz.pdp.Lesson7_vazifa1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.Lesson7_vazifa1.entity.*;
import uz.pdp.Lesson7_vazifa1.payload.TimeTableDto;
import uz.pdp.Lesson7_vazifa1.repository.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TimeTableController {


    @Autowired
    GroupsRepository groupsRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    TimeTableRepository timeTableRepository;



    //GET TimeTable
    @RequestMapping(value = "/time_table", method = RequestMethod.GET)
    public List<TimeTable> getTimeTable(){
        return  timeTableRepository.findAll();
    }


    //ADDED Mark
    @RequestMapping(value = "/time_table", method = RequestMethod.POST)
    public String addMark(@RequestBody TimeTableDto timeTableDto){
        Optional<Groups> optionalGroups = groupsRepository.findById(timeTableDto.getGroupID());
        if (optionalGroups.isPresent()) {
            Optional<Teacher> optionalTeacher = teacherRepository.findById(timeTableDto.getTeacherID());
            if (optionalTeacher.isPresent()) {
                Optional<Subject> optionalSubject = subjectRepository.findById(timeTableDto.getSubjectId());
                if (optionalSubject.isPresent()) {
                    TimeTable newTimeTable=new TimeTable();
                    newTimeTable.setToTime(timeTableDto.getToTime());
                    newTimeTable.setFromTime(timeTableDto.getFromTime());
                    newTimeTable.setGroup(optionalGroups.get());
                    newTimeTable.setTeacher(optionalTeacher.get());
                    newTimeTable.setSubject(optionalSubject.get());
                    timeTableRepository.save(newTimeTable);
                    return "TimeTable added";
                }else {
                    return "The subject you entered is not available in the database." +
                            " If you want to add this timeTable a Mark and then the subject will also be new," +
                            " first add a new subject to the Subject schedule, then continue again ...";
                }
            }else {
                return "The teacher you entered is not available in the database." +
                        " If you want to add this timeTable and then the teacher will also be new," +
                        " first add a new teacher to the Teacher schedule, then continue again ...";
            }
        }else {
            return "The group you entered is not available in the database." +
                    " If you want to add this timeTable  and then the group will also be new," +
                    " first add a new group to the Groups schedule, then continue again ...";
        }
    }

    //GET TimeTable BY ID
    @RequestMapping(value = "/time_table/{id}", method = RequestMethod.GET)
    public TimeTable getTimeTableById(@PathVariable Integer id){
        Optional<TimeTable> optionalTimeTable = timeTableRepository.findById(id);
        return optionalTimeTable.orElseGet(TimeTable::new);
    }

    //DELETE TimeTable
    @RequestMapping(value = "/time_table/{id}", method = RequestMethod.DELETE)
    public String deleteTimeTable(@PathVariable Integer id){
        List<TimeTable> timeTableList = timeTableRepository.findAll();
        for (TimeTable timeTable : timeTableList) {
            if (timeTable.getId().equals(id)){
                timeTableRepository.deleteById(id);
                return "TimeTable deleted";
            }
        }
        return "TimeTable not found";
    }

    //UPDATE TimeTable
    @RequestMapping(value = "/time_table/{id}", method = RequestMethod.PUT)
    public String updateTimeTable(@PathVariable Integer id, @RequestBody TimeTableDto timeTableDto){
        Optional<TimeTable> optionalTimeTable = timeTableRepository.findById(id);
        if (optionalTimeTable.isPresent()){
            Optional<Groups> optionalGroups = groupsRepository.findById(timeTableDto.getGroupID());
            if (optionalGroups.isPresent()) {
                Optional<Teacher> optionalTeacher = teacherRepository.findById(timeTableDto.getTeacherID());
                if (optionalTeacher.isPresent()) {
                    Optional<Subject> optionalSubject = subjectRepository.findById(timeTableDto.getSubjectId());
                    if (optionalSubject.isPresent()) {
                        TimeTable editedTimeTable=optionalTimeTable.get();
                        editedTimeTable.setToTime(timeTableDto.getToTime());
                        editedTimeTable.setFromTime(timeTableDto.getFromTime());
                        editedTimeTable.setGroup(optionalGroups.get());
                        editedTimeTable.setTeacher(optionalTeacher.get());
                        editedTimeTable.setSubject(optionalSubject.get());
                        timeTableRepository.save(editedTimeTable);
                        return "TimeTable edited";
                    }else {
                        return "The subject you entered is not available in the database." +
                                " If you want to edit this timeTable a Mark and then the subject will also be new," +
                                " first add a new subject to the Subject schedule, then continue again ...";
                    }
                }else {
                    return "The teacher you entered is not available in the database." +
                            " If you want to edit this timeTable and then the teacher will also be new," +
                            " first add a new teacher to the Teacher schedule, then continue again ...";
                }
            }else {
                return "The group you entered is not available in the database." +
                        " If you want to edit this timeTable  and then the group will also be new," +
                        " first add a new group to the Groups schedule, then continue again ...";
            }
        }else {
            return "TimeTable not found";
        }
    }
}
