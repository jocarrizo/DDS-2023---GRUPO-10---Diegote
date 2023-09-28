package presentation.dto;
import domain.Confianza;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MisDatos {

    private Long id;
    private Confianza categoria;
    private Double puntaje;

}