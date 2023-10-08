package Notificaciones;

import Servicios.Incidente;
import Usuarios.Perfil;
import Usuarios.Usuario;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class MedioNotificacion {
    @Id
    private String id_medio;

    @OneToMany(mappedBy = "medioNotificacion")
    private List<Perfil> perfiles;
    public abstract void notificar(Usuario usuario, List<Incidente> incidentes);
}