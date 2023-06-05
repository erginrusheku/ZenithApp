package zenithapp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@Entity
public class ZenithDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameWatch;
    private String description;
    private int price;

    public ZenithDTO(){}

    public ZenithDTO(Long id, String nameWatch, String description, int price) {
        this.id = id;
        this.nameWatch = nameWatch;
        this.description = description;
        this.price = price;
    }



}
