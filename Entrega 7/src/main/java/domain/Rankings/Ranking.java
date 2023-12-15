package domain.Rankings;

import domain.Entidades.Entidad;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
public class Ranking {
    @Id
    @GeneratedValue
    private int id_ranking;

    @Column(name = "TIPO")
    @Enumerated(EnumType.STRING)
    private Tipo_Ranking tipo_ranking;

    @ManyToOne
    @JoinColumn(name = "ID_INFORME")
    private Informe informe_asoc;

    @Column(name = "FECHA_CREACION")
    private Date fecha_creacion;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @OneToMany(mappedBy ="ranking_asoc" )
    private List<PosicionRanking> ranking = new ArrayList<>();

    public Ranking() {}

    public Ranking(Tipo_Ranking tipo_ranking, Informe informe_asoc, Date fecha_creacion, String descripcion, List<PosicionRanking> ranking) {
        this.tipo_ranking = tipo_ranking;
        this.informe_asoc = informe_asoc;
        this.fecha_creacion = fecha_creacion;
        this.descripcion = descripcion;
        this.ranking = ranking;
    }
}

