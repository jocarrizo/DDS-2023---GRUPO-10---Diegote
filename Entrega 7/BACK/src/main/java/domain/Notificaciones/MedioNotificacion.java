package domain.Notificaciones;

import domain.Servicios.Incidente;
import domain.Usuarios.Perfil;
import domain.Usuarios.Usuario;

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