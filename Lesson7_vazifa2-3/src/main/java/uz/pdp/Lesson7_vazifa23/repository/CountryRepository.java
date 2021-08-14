package uz.pdp.Lesson7_vazifa23.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.Lesson7_vazifa23.entity.Country;

public interface CountryRepository extends JpaRepository<Country,Integer> {
}
