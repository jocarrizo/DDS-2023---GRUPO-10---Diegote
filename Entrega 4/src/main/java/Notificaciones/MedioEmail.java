package Notificaciones;

import Servicios.Incidente;
import Usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.List;
@Getter
@Setter
@Entity
public class MedioEmail extends MedioNotificacion {
    private final String id_medio = "mail";
    public void notificar(Usuario usuario, List<Incidente> incidentes) {
        // TODO: Enviar email
    }
}