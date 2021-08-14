package uz.pdp.Lesson7_vazifa23.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.Lesson7_vazifa23.entity.Continent;

public interface ContinentRepository extends JpaRepository<Continent,Integer> {
}
