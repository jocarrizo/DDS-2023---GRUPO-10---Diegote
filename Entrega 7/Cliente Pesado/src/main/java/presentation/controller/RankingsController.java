package presentation.controller;

import domain.Rankings.Ranking;

import domain.Rankings.Tipo_Ranking;
import example.hibernate.utils.BDUtils;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingsController implements Handler {

    String hql = "FROM Ranking WHERE tipo_ranking = :tipo";

    public void handle(@NotNull Context ctx) throws Exception {

        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);

        Map<String, List<Ranking>> rankingsMap = new HashMap<>();

        try {
            for (Tipo_Ranking tipo : Tipo_Ranking.values()) {
                Query query = em.createQuery(hql, Ranking.class);
                query.setParameter("tipo", tipo);
                List<Ranking> resultados = query.getResultList();
                rankingsMap.put(tipo.name(), resultados);
            }

            BDUtils.commit(em);
            ctx.status(200);

            Map<String, Object> model = new HashMap<>();
            model.put("lista_rankings", rankingsMap);
            ctx.render("rankings.hbs", model);
        } catch (Exception e) {
            BDUtils.rollback(em);
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}

