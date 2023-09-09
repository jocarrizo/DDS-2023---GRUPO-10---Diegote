package Usuarios.Comunidades;

import Servicios.Incidente;
import Servicios.Monitoreable;
import Servicios.Servicio;
import Usuarios.Perfil;
import Usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "comunidad")
public class Comunidad {

    @Id
    @GeneratedValue
    private long id_comunidad;

    @ManyToMany
    @JoinTable(name="comunidad_x_perfil",
            joinColumns={@JoinColumn(name="id_comunidad")},
            inverseJoinColumns={@JoinColumn(name="id_perfil")})
    private List<Perfil> miembros;

    @Transient
    private List<Monitoreable> serviciosDeInteres;


    @Transient
    private List<Perfil>  administradores;

    @Transient
    private List<Incidente> incidentes;

    @Transient
    private List<Perfil> afectados;

    @Transient
    private List<Perfil> observadores;

    public Comunidad() {}

    public void notificarIncidente(Incidente I){};
    public void cerrarIncidente(Incidente I){};
}
