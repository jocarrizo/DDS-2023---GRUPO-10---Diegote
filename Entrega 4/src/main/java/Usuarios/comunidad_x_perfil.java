package Usuarios;

import Usuarios.Comunidades.Comunidad;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class comunidad_x_perfil {
    @EmbeddedId
    private comunidad_x_perfil_ID id;

    @ManyToOne
    @MapsId("id_comunidad")
    private Comunidad comunidad;

    @ManyToOne
    @MapsId("id_perfil")
    private Perfil perfil;

    @Column(name = "ADMIN")
    private boolean esAdmin = false;

    @Column(name="OBSERVADOR")
    private boolean esObservador = false;

    public comunidad_x_perfil() {}

    public comunidad_x_perfil(Comunidad comunidad, Perfil perfil) {
        this.comunidad = comunidad;
        this.perfil = perfil;
        this.id = new comunidad_x_perfil_ID(perfil.getId_perfil(),comunidad.getId_comunidad());
    }

    public comunidad_x_perfil(Comunidad comunidad, Perfil perfil, boolean esAdmin) {
        this.comunidad = comunidad;
        this.perfil = perfil;
        this.esAdmin = esAdmin;
        this.id = new comunidad_x_perfil_ID(perfil.getId_perfil(),comunidad.getId_comunidad());
    }

//TODO: ver el tema de agregar el atributo de si es observador

}
