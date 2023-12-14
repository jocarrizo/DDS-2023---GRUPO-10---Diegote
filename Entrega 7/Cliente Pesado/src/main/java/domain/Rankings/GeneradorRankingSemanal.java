package domain.Rankings;

import domain.Entidades.Entidad;
import java.util.List;


public abstract class GeneradorRankingSemanal {
    protected List<Entidad> entidades;

    public abstract List<Entidad> generarRanking();
}





