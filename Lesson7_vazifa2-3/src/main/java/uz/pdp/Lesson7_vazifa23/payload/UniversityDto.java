package uz.pdp.Lesson7_vazifa23.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data

public class UniversityDto {
    private String name;
    private Integer districtId;
    private String street;
    private String houseNumber;

}

