package domain.Rankings;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Informe {
    @Id
    @GeneratedValue
    private long id_informe;

    @OneToMany(mappedBy = "informe_asoc")
    private List<Ranking> ranking;

    @Column(name = "FECHA_CREACION")
    private Date fecha_creacion;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @OneToMany(mappedBy ="informe_asoc")
    private List<Ranking> rankings = new ArrayList<>();
}
