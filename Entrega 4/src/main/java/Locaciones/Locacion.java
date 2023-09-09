package Locaciones;

import Entidades.Entidad;
import Entidades.Establecimiento;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

public class Locacion {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id_localizacion;

    private List<Establecimiento> establecimientos;
    private UbicacionGeografica ubicacion;

}
