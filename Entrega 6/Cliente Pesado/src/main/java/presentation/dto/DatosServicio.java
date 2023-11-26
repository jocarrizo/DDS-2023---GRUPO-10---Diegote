package presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatosServicio {

    private long idServicio;
    private String nombreServicio;

    public DatosServicio(){}

    public DatosServicio(long idServicio, String nombreServicio) {
        this.idServicio = idServicio;
        this.nombreServicio = nombreServicio;
    }
}
