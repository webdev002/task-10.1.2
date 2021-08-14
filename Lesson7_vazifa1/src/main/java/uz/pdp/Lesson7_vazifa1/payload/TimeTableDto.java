package uz.pdp.Lesson7_vazifa1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeTableDto {
   private Date fromTime;
   private Date ToTime;
   private Integer subjectId;
   private Integer teacherID;
   private Integer groupID;
}
