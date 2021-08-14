package uz.pdp.Lesson7_vazifa23.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Continent {
    @Id
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;
}
