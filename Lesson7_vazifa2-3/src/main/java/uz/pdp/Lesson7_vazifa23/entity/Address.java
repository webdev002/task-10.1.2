package uz.pdp.Lesson7_vazifa23.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Address {
    @Id
    private Integer id;

    @ManyToOne
    private District district;

    private String street;

    private String houseNumber; // harflar ham ishtirok etishi mumkinligi uchun String qlib oldim
}
