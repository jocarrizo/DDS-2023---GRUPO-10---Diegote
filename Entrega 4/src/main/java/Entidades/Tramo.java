package Entidades;

import Servicios.Servicio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Tramo {

    @Id
    @GeneratedValue
    private long id_tramo;

    @OneToMany (mappedBy = "id_monitoreable")
    private List<Servicio> listaServicios;

    private String descripcion;
}
