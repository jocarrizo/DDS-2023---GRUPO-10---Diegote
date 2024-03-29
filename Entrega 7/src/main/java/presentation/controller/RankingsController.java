package presentation.controller;

import domain.Rankings.Informe;
import domain.Rankings.PosicionRanking;
import domain.Rankings.Ranking;

import domain.Rankings.Tipo_Ranking;
import example.hibernate.utils.BDUtils;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingsController implements Handler {

    String hql = "FROM Ranking WHERE tipo_ranking = :tipo";

    public void handle(@NotNull Context ctx) throws Exception {

        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);


        try {

            List<Informe> informes = em.createQuery("SELECT i FROM Informe i order by i.fecha_creacion DESC", Informe.class).getResultList();

            if(informes.size() == 0){
                new Exception("No hay informes");
            }
            List<Ranking> rankings = informes.get(0).getRanking();
            for(Ranking ranking : rankings){

                ranking.getRanking().sort(Comparator.reverseOrder());
            }

            BDUtils.commit(em);
            ctx.status(200).result("Ultimo informe renderizado");

            Map<String, Object> model = new HashMap<>();
            model.put("lista_rankings", rankings);
            ctx.render("rankingsLiviano.hbs", model);
        } catch (Exception e) {
            BDUtils.rollback(em);
            e.printStackTrace();
            ctx.status(400).result("No hay informes");
        } finally {
            em.close();
        }
    }
}

