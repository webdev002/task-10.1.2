package uz.pdp.Lesson7_vazifa23.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDto {
    private String firstName;
    private String lastName;
    private Integer age;
    private Integer groupId;
    private List<Integer> subjectsId;


}
