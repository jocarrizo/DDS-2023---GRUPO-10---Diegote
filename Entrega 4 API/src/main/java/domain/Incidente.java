package domain;


import java.util.Date;

public class Incidente {

    private long id_incidente;
    private Incidente monitoreable;
    private Comunidad comunidad;
    private Date apertura;
    private Date cierre;

    private int duracionIncidente() {

        long tiempoInicio = apertura.getTime();
        long tiempoCierre = cierre.getTime();
        long duracionEnMili = tiempoCierre - tiempoInicio;

        // Convert milliseconds to seconds and return
        return (int) (duracionEnMili / 60*1000);
    }

    public boolean incidenteFraudulento() {
        return this.duracionIncidente() < 3;

    }

}