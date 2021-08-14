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
public class TimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Date fromTime;

    @Column(nullable = false)
    private Date ToTime;

    @ManyToOne(optional = false)
    private Subject subject;

    @ManyToOne(optional = false)
    private Groups group;

    @ManyToOne(optional = false)
    private Teacher teacher;
}
