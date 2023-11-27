package domain.Locaciones;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Direccion {
    @Id
    @GeneratedValue
    private long id_direccion;

    @Column(name="CALLE")
    private String calle;
    @Column(name="ALTURA")
    private int altura;

    @OneToOne
    @JoinColumn(name = "id_ubicacion_geografica")
    private UbicacionGeografica ubicacionGeografica;
}
