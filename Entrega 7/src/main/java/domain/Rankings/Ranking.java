package domain.Rankings;

import domain.Entidades.Entidad;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Ranking {
    @Id
    @GeneratedValue
    private int id_ranking;

    @Column
    @Enumerated(EnumType.STRING)
    private Tipo_Ranking tipo_ranking;

    @OneToOne
    @JoinColumn(name = "ID_ENTIDAD")
    private Entidad entidad;

    @ManyToOne
    @JoinColumn(name = "ID_INFORME")
    private Informe informe_asoc;
}

