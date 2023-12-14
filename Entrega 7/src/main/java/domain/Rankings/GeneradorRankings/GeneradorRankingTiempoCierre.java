package domain.Rankings.GeneradorRankings;

import domain.Entidades.Entidad;
import domain.Rankings.PosicionRanking;
import domain.Rankings.Ranking;
import domain.Servicios.Incidente;
import example.hibernate.utils.BDUtils;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class GeneradorRankingTiempoCierre extends GeneradorRankingSemanal {

    @Override
    public void generarRanking(List<Entidad> lista){
        List<PosicionRanking> ranking = new ArrayList<>();
        float total_tardanza;
        int cantidad_incidentes;
        int milisecondsByDay = 86400000;
        int milisecondsByHour = 3600000;
        int dias;
        Date fechaactual = new Date(System.currentTimeMillis());
        for(Entidad entidad : lista){
            total_tardanza = 0;
            cantidad_incidentes = 0;
            for(Incidente incidente: entidad.getIncidentes()){

                dias = (int) ((fechaactual.getTime() - incidente.getApertura().getTime()) / milisecondsByDay);
                if(!incidente.isAbierto() && dias < 7){
                    total_tardanza += (int)((incidente.getCierre().getTime() - incidente.getApertura().getTime()) / milisecondsByHour);
                    cantidad_incidentes++;
                }
            }

            ranking.add(new PosicionRanking(total_tardanza / cantidad_incidentes, entidad));
        }
        ranking.sort(Comparator.reverseOrder());


        Ranking nuevoRanking = new Ranking(domain.Rankings.Tipo_Ranking.CANTIDAD_INCIDENTES,null,new Date(),"Ranking en funcion a cantidad de Incidentes.",ranking);


        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);

        em.persist(nuevoRanking);

        BDUtils.commit(em);
    }
}