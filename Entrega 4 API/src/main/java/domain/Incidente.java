package domain;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class Incidente {

    private long id_incidente;
    private Comunidad comunidad;
    private Date apertura;
    private Date cierre;
    private Long id_perfil_apertura;
    private Long id_perfil_cierre;

    private int duracionIncidente() {

        long tiempoInicio = apertura.getTime();
        long tiempoCierre = cierre.getTime();
        long duracionEnMili = tiempoCierre - tiempoInicio;

        return (int) (duracionEnMili / 60*1000);
    }

    public boolean incidenteFraudulento() {
        return this.duracionIncidente() < 3;

    }

}