package uz.pdp.Lesson7_vazifa1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherDto {
   private String firstName;
   private String lastName;
   private String age;
   private Integer schoolId;
   private Integer subjectId;
   private double salary;
}
