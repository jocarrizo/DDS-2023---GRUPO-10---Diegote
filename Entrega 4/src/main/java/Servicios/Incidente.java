package Servicios;
import Entidades.Entidad;
import Usuarios.Comunidades.Comunidad;
import Usuarios.Perfil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity
public class Incidente {
    @Id
    @GeneratedValue
    private long id_incidente;
    @Column
    private String observaciones;
    @Column
    private boolean abierto;
    @Column
    private Date apertura;
    @Column
    private Date cierre;

    @OneToOne
    @JoinColumn(name="ID_MONITOREABLE")
    private Monitoreable monitoreable;

    @ManyToOne
    @JoinColumn(name="ID_ENTIDAD")
    private Entidad entidad;

    @ManyToOne
    @JoinColumn(name = "ID_COMUNIDAD")
    private Comunidad comunidad;

    @ManyToOne
    @JoinColumn(name = "id_perfil_apertura")
    private Perfil id_perfil_apertura;

    @ManyToOne
    @JoinColumn(name = "id_perfil_cierre")
    private Perfil id_perfil_cierre;

    public Incidente(){}
    public Incidente(String observaciones, Monitoreable monitoreable, Entidad entidad, Comunidad comunidad, Perfil id_perfil_apertura){
        this.observaciones = observaciones;
        this.monitoreable = monitoreable;
        this.entidad = entidad;
        this.comunidad = comunidad;
        this.id_perfil_apertura = id_perfil_apertura;
        this.apertura = new Date();
    }

    private int duracionIncidente(){
        long tiempoInicio = apertura.getTime();
        long tiempoCierre = cierre.getTime();
        long duracionEnMili = tiempoCierre - tiempoInicio;
        return (int) (duracionEnMili / (60 * 1000)); // Dividir por 60,000 para obtener minutos.
    }

    public boolean incidenteFraudulento(){
        return this.duracionIncidente() < 3;
    }

    public void cerrarIncidente(Perfil perfil){
        id_perfil_cierre = perfil;
        cierre = new Date();
    }
}