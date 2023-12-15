package presentation.dto;

import domain.Rankings.PosicionRanking;
import domain.Rankings.Tipo_Ranking;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DatoRanking {
    private Tipo_Ranking tipo_ranking;
    private List<DatosPosicionRanking> entidades = new ArrayList<>();

    public DatoRanking() {}

    public DatoRanking(Tipo_Ranking tipo_ranking, List<PosicionRanking> entidades) {
        this.tipo_ranking = tipo_ranking;

        for (PosicionRanking posicion : entidades){
            this.entidades.add(new DatosPosicionRanking(posicion.getEntidad().getNombre(),posicion.getPuntaje()));
        }
    }
}
