package Entidades;

import Servicios.Monitoreable;
import Locaciones.Locacion;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Establecimiento {

    @Id
    @GeneratedValue
    private long id_establecimiento;

    @Column(name = "nombre")
    private String nombre;

    @Transient
    private Locacion locacion;

    @OneToMany( mappedBy = "establecimiento")
    private List<Tramo> tramos;

}
