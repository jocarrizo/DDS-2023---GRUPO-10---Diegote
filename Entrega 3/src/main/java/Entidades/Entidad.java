package Entidades;

import Locaciones.Locacion;
import Servicios.*;
import Usuarios.*;
import java.util.List;

public class Entidad {

    private String nombre;
    private List<Establecimiento> establecimientos;
    private TipoEntidad tipo;
    private Locacion locacion;
    private List<Incidente> incidentes;
    private List<Perfil> suscripciones;

    public void agregar_incidente(Incidente incidente){};
    public void agregar_incidente_oficial(Incidente incidente){};
    public void agregar_suscriptor(Perfil perfil){};
    public void remover_suscriptor(Perfil perfil){};

}

