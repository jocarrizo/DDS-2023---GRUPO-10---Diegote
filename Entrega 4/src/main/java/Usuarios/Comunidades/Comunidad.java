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
    @JoinTable(name="Comunidad_X_Perfil",
            joinColumns={@JoinColumn(name="id_comunidad")},
            inverseJoinColumns={@JoinColumn(name="id_perfil")})
    private List<Perfil> miembros;

    @OneToMany(mappedBy = "comunidad")
    private List<Monitoreable> serviciosDeInteres;

    @ManyToMany
    @JoinTable(name="Comunidad_X_Perfil",
            joinColumns={@JoinColumn(name="id_comunidad")},
            inverseJoinColumns={@JoinColumn(name="id_perfil")})
    private List<Perfil>  administradores;

    @OneToMany(mappedBy = "comunidad")
    private List<Incidente> incidentes;

    @ManyToMany
    @JoinTable(name="Comunidad_X_Perfil",
            joinColumns={@JoinColumn(name="id_comunidad")},
            inverseJoinColumns={@JoinColumn(name="id_perfil")})
    private List<Perfil> afectados;

    @ManyToMany
    @JoinTable(name="Comunidad_X_Perfil",
            joinColumns={@JoinColumn(name="id_comunidad")},
            inverseJoinColumns={@JoinColumn(name="id_perfil")})
    private List<Perfil> observadores;

    public void notificarIncidente(Incidente I){};
    public void cerrarIncidente(Incidente I){};
}
