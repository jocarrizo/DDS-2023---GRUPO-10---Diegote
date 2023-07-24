package Notificaciones;

import Servicios.Incidente;
import Usuarios.Usuario;

import java.util.List;

public abstract class EstrategiaNotificacion {
    private MedioNotificacion medioNotificaion;

    public void notificar(Usuario usuario, List<Incidente> incidentes){

    }
}
