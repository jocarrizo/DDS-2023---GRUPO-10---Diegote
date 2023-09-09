package Entidades;

import Servicios.Monitoreable;
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

    @Transient
    private Monitoreable monitoreable;

    @Column
    private String descripcion;

    @ManyToOne()
    @JoinColumn(name = "establecimiento_id")
    private Establecimiento establecimiento;
}
