package domain.Rankings.GeneradorRankings;

import domain.Entidades.Entidad;
import domain.Rankings.PosicionRanking;
import domain.Rankings.Ranking;
import domain.Servicios.Incidente;
import example.hibernate.utils.BDUtils;
import presentation.POST.PostCerrarIncidenteHandler;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GeneradorRankingCantIncidentes extends GeneradorRankingSemanal {
    @Override
    public void generarRanking(List<Entidad> lista){
        //mayor cantidad de incidentes reportados en 1 semana que hayan durado abiertos mas de 24 hs ??????
        List<PosicionRanking> ret = new ArrayList<>();

        for(Entidad entidad : lista){
            Date fecha = new Date();

            ret.add(new PosicionRanking(entidad.getIncidentes().stream()
                    .filter(i -> (!(((fecha.getTime() - i.getApertura().getTime())/(60*60*1000) < 24) && i.isAbierto()))
                            && (((fecha.getTime() - i.getApertura().getTime())/(60*60*1000) < 168)))
                    .count(),entidad));
        }

        Ranking nuevoRanking = new Ranking(domain.Rankings.Tipo_Ranking.CANTIDAD_INCIDENTES,null,new Date(),"Ranking en funcion a cantidad de Incidentes.",ret);

        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);

        em.persist(nuevoRanking);

        BDUtils.commit(em);
    }

}