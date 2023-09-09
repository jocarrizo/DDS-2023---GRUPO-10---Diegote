package Usuarios;

import Locaciones.Locacion;
import Notificaciones.EstrategiaNotificacion;
import Servicios.Monitoreable;
import Servicios.Incidente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Perfil {
    @Id
    @GeneratedValue
    private long id_perfil;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @Transient
    private List<Monitoreable> suscripciones;

    @Transient
    private Locacion locacion;

    @Transient
    private EstrategiaNotificacion estrategiaNotificacion;

    @ManyToOne()
    @JoinColumn(name="id_usuario")
    private Usuario id_usuario_asoc;

    public Perfil(){}

    private boolean esDeInteres(){
        return true;
    }

    private void notificar(Incidente I){
        return ;
    }

    private void notificarOficial(Incidente I){
        return ;
    }

}
