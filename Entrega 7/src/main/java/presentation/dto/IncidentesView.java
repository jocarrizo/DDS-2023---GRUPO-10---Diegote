package presentation.dto;

import com.github.javafaker.Bool;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter @Setter
public class IncidentesView {
    Long idIncidente;
    String observaciones;
    Date apertura;
    Date cierre;
    String estado;

    public IncidentesView(Long idIncidente, String observaciones, Date apertura, Date cierre, Boolean abierto) {
        this.idIncidente = idIncidente;
        this.observaciones = observaciones;
        this.apertura = apertura;
        this.cierre = cierre;
        this.estado = (abierto != null && abierto) ? "abierto" : "cerrado";
    }
}
