package pdp.uz.task10.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.awt.*;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String hotelName;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String floor;
    @Column(nullable = false)
    private String size;


    public void deleteById(Integer id) {
    }

}
