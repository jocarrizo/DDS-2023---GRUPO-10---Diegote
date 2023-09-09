package Entidades;

import Servicios.Monitoreable;
import Servicios.Servicio;
import Locaciones.Locacion;
import Servicios.GrupoServicio;
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

    @Column(name = "locacion")
    @ManyToOne
    @JoinColumn(name = "id_localizacion")
    private Locacion locacion;

    @Column(name = "tramo")
    @ManyToOne
    @JoinColumn(name = "id_tramo")
    private List<Tramo> tramos;

    @Column(name = "monitoreable")
    @OneToOne
    @JoinColumn(name = "id_monitoreable")
    private Monitoreable monitoreable;

}
