
package domain;

import javax.persistence.*;
import javax.transaction.TransactionScoped;
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

    @Column
    private long id_perfil_apertura;
    @Column
    private long id_perfil_cierre;

/*
    @ManyToOne
    @JoinColumn(name = "id_perfil_apertura")
    private Perfil perfil_apertura;

    @OneToOne
    @JoinColumn(name = "id_perfil_cierre")
    private Perfil perfil_cierre;
*/

    public Incidente(){}

    public Incidente(String observaciones, Date apertura, Date cierre, Perfil id_perfil_apertura, Perfil id_perfil_cierre) {
        this.observaciones = observaciones;
        this.apertura = apertura;
        this.cierre = cierre;
//        this.perfil_apertura = id_perfil_apertura;
//        this.perfil_cierre = id_perfil_cierre;
        this.id_perfil_apertura = id_perfil_apertura.getId_perfil();
        this.id_perfil_cierre = id_perfil_apertura.getId_perfil();
    }

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
/*
    public Perfil perfil_apertura() {
        return perfil_apertura;
    }

    public void set_perfil_apertura(Perfil perfil_apertura) {
        this.perfil_apertura = perfil_apertura;
    }
*/

    public Long getId_perfil_cierre() {
        return id_perfil_cierre;
    }

    public void setId_perfil_cierre(Perfil id_perfil_cierre) {
        this.id_perfil_cierre = id_perfil_cierre;
    }

    public boolean incidenteFraudulento() {
        return this.duracionIncidente() < 3;
    }
}