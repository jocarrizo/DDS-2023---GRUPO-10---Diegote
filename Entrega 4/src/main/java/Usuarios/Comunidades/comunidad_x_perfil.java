package Usuarios.Comunidades;

import Usuarios.Perfil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

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

    @Column(name="AFECTADO")
    private boolean esAfectado = false;

    public comunidad_x_perfil() {}

    public comunidad_x_perfil(Comunidad comunidad, Perfil perfil, boolean esAdmin, boolean esObservador, boolean esAfectado) {
        this.comunidad = comunidad;
        this.perfil = perfil;
        this.esAdmin = esAdmin;
        this.esObservador = esObservador;
        this.esAfectado = esAfectado;
        this.id = new comunidad_x_perfil_ID(perfil.getId_perfil(),comunidad.getId_comunidad());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        comunidad_x_perfil that = (comunidad_x_perfil) o;
        return esAdmin == that.esAdmin && esObservador == that.esObservador && esAfectado == that.esAfectado && Objects.equals(id, that.id) && Objects.equals(comunidad, that.comunidad) && Objects.equals(perfil, that.perfil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comunidad, perfil, esAdmin, esObservador, esAfectado);
    }


    //TODO: ver el tema de agregar el atributo de si es observador

}
