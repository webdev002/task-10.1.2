package uz.pdp.Lesson7_vazifa1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

import javax.persistence.Column;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MarkDto {
   private  String quantityOfMark;
   private  Integer subjectId;
   private  Integer studentId;
   private  Integer teacherId;
   private  Date date;
}
