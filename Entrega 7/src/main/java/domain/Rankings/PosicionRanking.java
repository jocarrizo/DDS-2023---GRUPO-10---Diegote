package domain.Rankings;

import domain.Entidades.Entidad;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Comparator;


@Getter
@Setter
@Entity
public class PosicionRanking implements Comparable<PosicionRanking> {
    @Id
    @Column(name="ID")
    private long id_posicion;

    @Column(name="PUNTAJE")
    private float puntaje;

    @ManyToOne
    @JoinColumn(name = "ID_ENTIDAD")
    private Entidad entidad;

    @ManyToOne
    @JoinColumn(name="ID_RANKING")
    private Ranking ranking_asoc;

    public PosicionRanking() {}

    public PosicionRanking(float puntaje, Entidad entidad) {
        this.puntaje = puntaje;
        this.entidad = entidad;
    }
    public int compareTo(PosicionRanking otraPosicion){
        return Float.compare(this.puntaje, otraPosicion.getPuntaje());
    }
}
