package Usuarios.Comunidades;

import Servicios.Incidente;
import Servicios.Monitoreable;
import Servicios.Servicio;
import Usuarios.Perfil;
import Usuarios.Usuario;
import Usuarios.comunidad_x_perfil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "comunidad")
public class Comunidad {
    @Id
    @GeneratedValue
    @Column (name = "ID")
    private long id_comunidad;

    @OneToMany(mappedBy = "comunidad")
    private List<comunidad_x_perfil> miembros = new ArrayList<>();

    @Transient
    private List<Monitoreable> serviciosDeInteres;

    @Transient
    private List<Incidente> incidentes;

    @Transient
    private List<Perfil> afectados;


    public Comunidad(){}

    public void addMiembro(Perfil s){
        comunidad_x_perfil nuevo = new comunidad_x_perfil(this, s);
        if(!miembros.contains(nuevo)){
            miembros.add(nuevo);
            s.getComunidades().add(nuevo);
        }
    }

    public void addAdministrador(Perfil s){
        comunidad_x_perfil nuevo = new comunidad_x_perfil(this, s,true);
        if(!miembros.contains(nuevo)){
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
}
