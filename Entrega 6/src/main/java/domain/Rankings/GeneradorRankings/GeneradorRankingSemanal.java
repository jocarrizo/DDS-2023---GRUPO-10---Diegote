package domain.Rankings.GeneradorRankings;

import domain.Entidades.Entidad;
import domain.Rankings.PosicionRanking;

import java.util.ArrayList;
import java.util.List;


public abstract class GeneradorRankingSemanal {
    protected List<PosicionRanking> entidades = new ArrayList<>();

    public abstract void generarRanking(List<Entidad> lista);

}





