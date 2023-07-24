package Usuarios;

import Locaciones.Locacion;
import Notificaciones.EstrategiaNotificacion;
import Servicios.Monitoreable;
import Servicios.Incidente;

import java.util.List;

public class Perfil {
    private String nombre;
    private String apellido;
    private String usuario;
    private List<Monitoreable> suscripciones;
    private Locacion locacion;
    private EstrategiaNotificacion estrategiaNotificacion;

    private boolean esDeInteres(){
        return true;
    }

    private void notificar(Incidente I){
        return ;
    }

    private void notificarOficial(Incidente I){
        return ;
    }

}
