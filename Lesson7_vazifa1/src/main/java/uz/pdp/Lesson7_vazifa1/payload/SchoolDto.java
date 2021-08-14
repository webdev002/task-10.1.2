package uz.pdp.Lesson7_vazifa1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SchoolDto {
   private String schoolName;
   private String country;

   private String region;

   private String district;

   private String street;

   private String home;


}
