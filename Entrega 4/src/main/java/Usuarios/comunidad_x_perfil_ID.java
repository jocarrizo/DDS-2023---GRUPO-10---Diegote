package Usuarios;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;


@Setter
@Getter
@Embeddable
public class comunidad_x_perfil_ID implements Serializable {
    @Column(name = "ID_PERFIL")
    private long id_perfil;

    @Column(name = "ID_COMUNIDAD")
    private long id_comunidad;

    public comunidad_x_perfil_ID() {}

    public comunidad_x_perfil_ID(long id_perfil, long id_comunidad) {
        this.id_perfil = id_perfil;
        this.id_comunidad = id_comunidad;
    }
}
