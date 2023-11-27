package domain.Locaciones;

import domain.Entidades.Establecimiento;
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

    @ManyToOne
    @JoinColumn(name = "ID_UBICACION_GEOGRAFICA")
    private UbicacionGeografica ubicacion;

    @Column(name="ALTURA")
    private int altura;

    public Locacion() {}

    public Locacion(List<Establecimiento> establecimientos, UbicacionGeografica ubicacion) {
        this.establecimientos = establecimientos;
        this.ubicacion = ubicacion;

    }
}
