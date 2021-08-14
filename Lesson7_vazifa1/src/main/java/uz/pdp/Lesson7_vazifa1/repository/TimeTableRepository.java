package uz.pdp.Lesson7_vazifa1.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.Lesson7_vazifa1.entity.Student;
import uz.pdp.Lesson7_vazifa1.entity.TimeTable;

public interface TimeTableRepository extends JpaRepository<TimeTable, Integer> {
}
