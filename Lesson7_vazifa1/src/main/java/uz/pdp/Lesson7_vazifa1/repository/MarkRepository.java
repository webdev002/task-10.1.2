package uz.pdp.Lesson7_vazifa1.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.Lesson7_vazifa1.entity.Mark;
import uz.pdp.Lesson7_vazifa1.entity.Student;

public interface MarkRepository extends JpaRepository<Mark, Integer> {
}
