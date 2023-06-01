package Entidades;

import Servicios.Servicio;
import Locaciones.Locacion;
import Servicios.GrupoServicio;
import java.util.List;

public class Establecimiento {

    private String nombre;
    private Locacion locacion;
    private List<Tramo> tramos;
    private List<Servicio> servicios;
    private List<GrupoServicio> grupoServicios;

}
