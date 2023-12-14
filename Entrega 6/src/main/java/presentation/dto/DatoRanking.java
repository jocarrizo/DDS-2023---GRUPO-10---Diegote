package presentation.dto;

import domain.Rankings.PosicionRanking;
import domain.Rankings.Tipo_Ranking;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DatoRanking {
    private Tipo_Ranking tipo_ranking;
    private List<PosicionRanking> entidades;

    public DatoRanking() {}

    public DatoRanking(Tipo_Ranking tipo_ranking, List<PosicionRanking> entidades) {
        this.tipo_ranking = tipo_ranking;
        this.entidades = entidades;
    }
}
