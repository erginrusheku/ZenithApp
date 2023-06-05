package zenithapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ZenithDTO {
    private Long id;
    private String nameWatch;
    private String description;
    private int price;
}
