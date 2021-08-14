package uz.pdp.Lesson7_vazifa1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupsDto {
    private String groupName;
    private Integer studentsNumber;
    private Integer schoolid;
}
