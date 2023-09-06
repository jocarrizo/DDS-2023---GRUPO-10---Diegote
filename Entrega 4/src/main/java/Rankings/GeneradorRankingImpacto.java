package Rankings;

import Entidades.Entidad;

import java.util.List;

public class GeneradorRankingImpacto extends GeneradorRankingSemanal {

    @Override
    public List<Entidad> generarRanking(){

        return entidades;
    }
}