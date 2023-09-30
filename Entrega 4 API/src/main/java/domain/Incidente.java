
package domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Incidente {
    @Id
    @GeneratedValue
    private long id_incidente;

    @Column
    private String observaciones;

    @Column
    private Date apertura;

    @Column
    private Date cierre;

    @ManyToOne
    @JoinColumn(name = "id_perfil_apertura")
    private Perfil id_perfil_apertura;

    @OneToOne
    @JoinColumn(name = "id_perfil_cierre")
    private Perfil id_perfil_cierre;

    private int duracionIncidente() {
        long tiempoInicio = apertura.getTime();
        long tiempoCierre = cierre.getTime();
        long duracionEnMili = tiempoCierre - tiempoInicio;
        return (int) (duracionEnMili / (60 * 1000)); // Dividir por 60,000 para obtener minutos.
    }

    public long getId_incidente() {
        return id_incidente;
    }

    public void setId_incidente(long id_incidente) {
        this.id_incidente = id_incidente;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getApertura() {
        return apertura;
    }

    public void setApertura(Date apertura) {
        this.apertura = apertura;
    }

    public Date getCierre() {
        return cierre;
    }

    public void setCierre(Date cierre) {
        this.cierre = cierre;
    }

    public Perfil getId_perfil_apertura() {
        return id_perfil_apertura;
    }

    public void setId_perfil_apertura(Perfil id_perfil_apertura) {
        this.id_perfil_apertura = id_perfil_apertura;
    }

    public Perfil getId_perfil_cierre() {
        return id_perfil_cierre;
    }

    public void setId_perfil_cierre(Perfil id_perfil_cierre) {
        this.id_perfil_cierre = id_perfil_cierre;
    }

    public boolean incidenteFraudulento() {
        return this.duracionIncidente() < 3;
    }
}