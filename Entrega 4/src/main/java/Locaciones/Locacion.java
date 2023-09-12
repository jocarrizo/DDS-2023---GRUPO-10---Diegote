package Locaciones;

import Entidades.Establecimiento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Locacion {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id_localizacion;

    @Transient
    private List<Establecimiento> establecimientos;

    @Transient
    private UbicacionGeografica ubicacion;

}
