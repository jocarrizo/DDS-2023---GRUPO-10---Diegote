package presentation.dto;

import com.github.javafaker.Bool;

import java.util.Date;


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
