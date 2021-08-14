package pdp.uz.task10.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDto {
    private String hotelName;
    private String roomNumber;
    private String roomSize;
    private String roomFloor;
}
