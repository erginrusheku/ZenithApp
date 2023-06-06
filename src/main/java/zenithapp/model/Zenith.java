package zenithapp.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@Entity
public class Zenith {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameWatch;
    private String description;
    private int price;

    public Zenith(){}

    public Zenith(Long id, String nameWatch, String description, int price) {

    }



}
