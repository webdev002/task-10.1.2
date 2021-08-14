package uz.pdp.Lesson7_vazifa1.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String quantityOfMark;

    @ManyToOne(optional = false)
    private Subject  subject;

    @ManyToOne(optional = false)
    private Student student;

    @ManyToOne(optional = false)
    private Teacher  teacher;

    @Column(nullable = false)
    private Date date;

}
