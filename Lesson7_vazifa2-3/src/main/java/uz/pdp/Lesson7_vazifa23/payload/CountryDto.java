package uz.pdp.Lesson7_vazifa23.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CountryDto {
    private String name;
    private Integer continentId;
}
