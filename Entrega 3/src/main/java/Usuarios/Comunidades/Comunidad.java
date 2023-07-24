package Usuarios.Comunidades;

import Servicios.Incidente;
import Servicios.Monitoreable;
import Servicios.Servicio;
import Usuarios.Perfil;
import Usuarios.Usuario;

import java.util.List;

public class Comunidad {

    private List<Perfil> miembros;
    private Monitoreable serviciosDeInteres;
    private List<Perfil>  administradores;
    private List<Incidente> incidentes;
    private List<Perfil> afectados;
    private List<Perfil> observadores;

    public void notificarIncidente(Incidente I){};
    public void cerrarIncidente(Incidente I){};
}
