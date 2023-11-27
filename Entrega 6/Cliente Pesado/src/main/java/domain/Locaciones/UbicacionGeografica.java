package domain.Locaciones;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class UbicacionGeografica {
    @Id
    @GeneratedValue
    private long id_ubicacion_geografica;

    @ManyToOne
    @JoinColumn(name = "id_municipio")
    private Municipio municipio;

    @Column(name="BARRIO")
    private String barrio;

    @OneToOne(mappedBy = "ubicacionGeografica", cascade = CascadeType.ALL, orphanRemoval = true)
    private Direccion direccion;

    public UbicacionGeografica() {}

    public UbicacionGeografica(long id_ubicacion_geografica, Municipio municipio, String barrio, Direccion direccion) {
        this.id_ubicacion_geografica = id_ubicacion_geografica;
        this.municipio = municipio;
        this.barrio = barrio;
        this.direccion = direccion;
    }
}
