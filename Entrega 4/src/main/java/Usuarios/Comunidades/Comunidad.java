package Usuarios.Comunidades;

import Servicios.Incidente;
import Servicios.Monitoreable;
import Usuarios.Confianza;
import Usuarios.Perfil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "comunidad")
public class Comunidad {
    @Id
    @GeneratedValue
    @Column (name = "ID")
    private long id_comunidad;
    @Column
    private Double puntaje;
    @Enumerated(EnumType.STRING)
    private Confianza confianza;

    @OneToMany(mappedBy = "comunidad")
    private List<comunidad_x_perfil> miembros = new ArrayList<comunidad_x_perfil>();

    //TODO
    @Transient
    private List<Monitoreable> serviciosDeInteres;

    @OneToMany(mappedBy = "comunidad",
            cascade = CascadeType.ALL,
            orphanRemoval = true )
    private List<Incidente> incidentes;

    //TODO
    @Transient
    private List<Perfil> afectados;

    public Comunidad(){}

    public void addMiembro(Perfil s){
        agregarMiembro(s, false, false,false);
    }

    public void addAdministrador(Perfil s) {
        agregarMiembro(s, true, false,false);
    }

    public void addObservador(Perfil s) {
        agregarMiembro(s, false, true,false);
    }

    private void agregarMiembro(Perfil s, boolean esAdministrador, boolean esObservador, boolean esAfectado) {
        comunidad_x_perfil nuevo = new comunidad_x_perfil(this, s, esAdministrador,esObservador,esAfectado);

        if (!miembros.contains(nuevo)) {
            miembros.add(nuevo);
            s.getComunidades().add(nuevo);
        }
    }

    public void removeMiembro(Perfil s){
        for (Iterator<comunidad_x_perfil> iterator = miembros.iterator();
             iterator.hasNext(); ) {
            comunidad_x_perfil registro = iterator.next();

            if (registro.getComunidad().equals(this) &&
                    registro.getPerfil().equals(s)) {
                iterator.remove();
                registro.getPerfil().getComunidades().remove(registro);
                registro.setComunidad(null);
                registro.setPerfil(null);
            }
        }
    }

    public void notificarIncidente(Incidente I){};

    public void cerrarIncidente(Incidente I){};

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comunidad comunidad = (Comunidad) o;
        return id_comunidad == comunidad.id_comunidad && Objects.equals(miembros, comunidad.miembros) && Objects.equals(serviciosDeInteres, comunidad.serviciosDeInteres) && Objects.equals(incidentes, comunidad.incidentes) && Objects.equals(afectados, comunidad.afectados);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id_comunidad, miembros, serviciosDeInteres, incidentes, afectados);
    }

}
