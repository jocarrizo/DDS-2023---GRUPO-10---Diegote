package domain.Entidades;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
public class EntidadPrestadora {
    @Id
    @GeneratedValue
    private long id_entidad_prestadora;

    @Column(name="NOMBRE")
    private String nombre;

    @OneToMany(mappedBy = "entidad_prestadora")
    private List<Entidad> entidades;
}
