package Usuarios;

import Locaciones.Locacion;
import Notificaciones.EstrategiaNotificacion;
import Servicios.Monitoreable;
import Servicios.Incidente;
import Usuarios.Comunidades.Comunidad;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Perfil {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id_perfil;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @OneToOne
    @JoinColumn(name="ID_MONITOREABLE")
    private Monitoreable monitoreable;

    @Transient
    private Locacion locacion;

    @Transient
    private EstrategiaNotificacion estrategiaNotificacion;

    @ManyToOne()
    @JoinColumn(name="ID_USUARIO")
    private Usuario usuario_asoc;

    @OneToMany(mappedBy = "perfil")
    private List<comunidad_x_perfil> comunidades = new ArrayList<>();

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
