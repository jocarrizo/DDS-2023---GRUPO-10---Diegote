package Usuarios.Comunidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        comunidad_x_perfil_ID that = (comunidad_x_perfil_ID) o;
        return id_perfil == that.id_perfil && id_comunidad == that.id_comunidad;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_perfil, id_comunidad);
    }
}
