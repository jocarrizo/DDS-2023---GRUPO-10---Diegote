package domain.Notificaciones;

import domain.Servicios.Incidente;
import domain.Usuarios.Usuario;

import java.util.List;

public abstract class EstrategiaNotificacion {
    private MedioNotificacion medioNotificaion;

    public void notificar(Usuario usuario, List<Incidente> incidentes){ }
}
