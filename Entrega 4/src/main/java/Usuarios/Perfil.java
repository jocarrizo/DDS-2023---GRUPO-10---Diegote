package Usuarios;

import Entidades.Entidad;
import Locaciones.Locacion;
import Notificaciones.EstrategiaNotificacion;
import Notificaciones.MedioNotificacion;
import Servicios.Monitoreable;
import Servicios.Incidente;
import Usuarios.Comunidades.Comunidad;
import Usuarios.Comunidades.comunidad_x_perfil;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@NaturalIdCache
@Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE
)
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

    @OneToOne
    @JoinColumn(name="ID_LOCACION")
    private Locacion locacion;

    @ManyToOne
    @JoinColumn(name="MEDIO_NOT_FAV")
    private MedioNotificacion medioNotificacion;

    @Column
    private Double puntaje = 5.0;

    @Enumerated(EnumType.STRING)
    private Confianza confianza;

    @ManyToOne()
    @JoinColumn(name="ID_USUARIO")
    private Usuario usuario_asoc;

    @OneToMany(mappedBy = "perfil",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<comunidad_x_perfil> comunidades = new ArrayList<>();

    @OneToMany(mappedBy = "perfil_asoc")
    private List<Horario> horarios;
    public Perfil(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Perfil perfil = (Perfil) o;
        return Objects.equals(puntaje, perfil.puntaje) && confianza == perfil.confianza && id_perfil == perfil.id_perfil && Objects.equals(nombre, perfil.nombre) && Objects.equals(apellido, perfil.apellido) && Objects.equals(monitoreable, perfil.monitoreable) && Objects.equals(locacion, perfil.locacion) && Objects.equals(medioNotificacion, perfil.medioNotificacion) && Objects.equals(usuario_asoc, perfil.usuario_asoc) && Objects.equals(comunidades, perfil.comunidades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_perfil, nombre, apellido, monitoreable, locacion, medioNotificacion, usuario_asoc, comunidades);
    }

    private boolean esDeInteres(){
        return true;
    }

    private void notificar(Incidente I){
        return ;
    }

    private void notificarOficial(Incidente I){
        return ;
    }

    public void abrirIncidente(){
        //TODO
        new Incidente("OBSERVACIONES", new Monitoreable(), new Entidad(), new Comunidad(), this);
    }

    public void cerrarIncidente(Incidente incidente){
        //TODO
        incidente.cerrarIncidente(this);
    }
}
