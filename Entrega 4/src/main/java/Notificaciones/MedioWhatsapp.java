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
public class MedioWhatsapp extends MedioNotificacion {
    private final String id_medio = "Whatsapp";
    public void notificar(Usuario usuario, List<Incidente> incidentes) {
        // Enviar mensaje por Whatsapp
    }
}