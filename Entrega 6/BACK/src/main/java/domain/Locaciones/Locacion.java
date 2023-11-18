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

    @Transient
    private UbicacionGeografica ubicacion;

    @Column
    private String
            nombre_municipio,
            nombre_departamento,
            nombre_provincia,
            nombre_barrio,
            calle;

    @Column
    private int altura;

    public Locacion() {}

    public Locacion(List<Establecimiento> establecimientos, UbicacionGeografica ubicacion) {
        this.establecimientos = establecimientos;
        this.ubicacion = ubicacion;

        this.nombre_municipio = ubicacion.getMunicipio().getNombre();
        this.nombre_departamento = ubicacion.getMunicipio().getDepartamento().getNombre();
        this.nombre_provincia = getUbicacion().getMunicipio().getProvincia().getNombre();
        this.nombre_barrio = getUbicacion().getBarrio();
        this.calle = getUbicacion().getDireccion().getCalle();
        this.altura = getUbicacion().getDireccion().getAltura();
    }
}
