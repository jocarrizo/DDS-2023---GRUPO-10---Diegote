package presentation.controller;

import example.hibernate.utils.BDUtils;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import presentation.dto.IncidentesView;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;

public class incidentesPorEstadoController implements Handler{

    String sql_comunidad = "SELECT id_comunidad FROM COMUNIDAD_X_PERFIL WHERE id_perfil = :1";

    @Override
    public void handle(@NotNull Context ctx) throws Exception{

        String IDSESION = ctx.formParam("IDSESION");
        String FILTER = ctx.formParam("FILTER");
        Long ID_COMUNIDAD = Long.valueOf(ctx.formParam("COMUNIDAD"));
        List<IncidentesView> incidentes = new ArrayList<>();

        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);

        try {
            Query query_comunidad = em.createNativeQuery(sql_comunidad);
            query_comunidad.setParameter(1, IDSESION);
            List<Long> comunidades = query_comunidad.getResultList();

            if (ID_COMUNIDAD != -1) {

                String hql_incidentes = "SELECT NEW presentation.dto.IncidentesView(i.id_incidente,i.observaciones,i.apertura,i.cierre, i.abierto) FROM Incidente i where i.comunidad=?1";

                if (Objects.equals(FILTER, "abierto")){
                    hql_incidentes = hql_incidentes.concat(" AND i.abierto=true");
                } else if (Objects.equals(FILTER, "cerrado")) {
                    hql_incidentes = hql_incidentes.concat(" AND i.abierto=false");
                }

                incidentes.addAll(em.createQuery(hql_incidentes, IncidentesView.class)
                        .setParameter(1, ID_COMUNIDAD)
                        .getResultList());
            }


            BDUtils.commit(em);
            ctx.status(200);

            Map<String, Object> model = new HashMap<>();
            model.put("comunidades", comunidades);
            model.put("incidentes", incidentes);
            ctx.render("incidentesPorEstado.hbs", model);
        } catch (Exception e) {
            BDUtils.rollback(em);
            e.printStackTrace();
        } finally {
            em.close();
        }



    }

}
