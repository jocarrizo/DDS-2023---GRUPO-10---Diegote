package Notificaciones;

import Servicios.Incidente;
import Usuarios.Usuario;

import java.util.List;

public abstract class MedioNotificacion {
    public abstract void notificar(Usuario usuario, List<Incidente> incidentes);
}