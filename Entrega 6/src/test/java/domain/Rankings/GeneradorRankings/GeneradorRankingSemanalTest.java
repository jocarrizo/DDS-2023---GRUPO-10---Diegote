package domain.Rankings.GeneradorRankings;

import domain.Rankings.PosicionRanking;
import domain.Rankings.Ranking;
import domain.Rankings.Tipo_Ranking;

import java.util.Date;
import java.util.List;

class GeneradorRankingSemanalTest {

    void generarRankings(){

        new Ranking(Tipo_Ranking.IMPACTO,null,new Date(),"descripcion 1",null);

    }
}