package uz.pdp.Lesson7_vazifa1.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.Lesson7_vazifa1.entity.Address;
import uz.pdp.Lesson7_vazifa1.entity.Groups;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
