package domain.Rankings;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Informe {
    @Id
    @GeneratedValue
    private int indice_informe;

    @Column
    private int id_informe;

    @OneToMany(mappedBy = "informe_asoc")
    private List<Ranking> ranking;
}
