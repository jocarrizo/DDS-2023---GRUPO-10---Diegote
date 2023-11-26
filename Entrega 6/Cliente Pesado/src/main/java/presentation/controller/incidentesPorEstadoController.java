package presentation.controller;

import example.hibernate.utils.BDUtils;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.NotNull;
import presentation.dto.ComunidadAux;
import presentation.dto.IncidentesView;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;

public class incidentesPorEstadoController implements Handler{

    String hql_comunidad = "SELECT c.comunidad FROM comunidad_x_perfil c WHERE c.perfil.id_perfil = :perfilito";
    @Override
    public void handle(@NotNull Context ctx) throws Exception{

        Long IDUSUARIO = ctx.pathParamAsClass("IDUSUARIO", Long.class).get();
        String FILTRO_INCIDENTE = ctx.pathParamAsClass("FILTRO", String.class).get();
        Long COMUNIDAD_INCIDENTE = ctx.pathParamAsClass("COMUNIDAD", Long.class).get();

        ComunidadAux comunidad_aux = new ComunidadAux(IDUSUARIO,FILTRO_INCIDENTE, COMUNIDAD_INCIDENTE);

        List<IncidentesView> incidentes = new ArrayList<>();

        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);

        try {
            Query query_comunidad = em.createQuery(hql_comunidad);
            query_comunidad.setParameter("perfilito", comunidad_aux.getIDUSUARIO());
            List<Long> comunidades = query_comunidad.getResultList();

            if (comunidad_aux.getCOMUNIDAD() != -1) {

                String hql_incidentes = "SELECT NEW presentation.dto.IncidentesView(i.id_incidente,i.observaciones,i.apertura,i.cierre, i.abierto) FROM Incidente i where i.comunidad=?1";

                if (Objects.equals(comunidad_aux.getFILTRO(), "Abierto")){
                    hql_incidentes = hql_incidentes.concat(" AND i.abierto=true");
                } else if (Objects.equals(comunidad_aux.getFILTRO(), "Cerrado")) {
                    hql_incidentes = hql_incidentes.concat(" AND i.abierto=false");
                }

                incidentes.addAll(em.createQuery(hql_incidentes, IncidentesView.class)
                        .setParameter(1, comunidad_aux.getCOMUNIDAD())
                        .getResultList());
            }


            BDUtils.commit(em);
            ctx.status(200);

            Map<String, Object> model = new HashMap<>();
            model.put("comunidades", comunidades);
            model.put("comunidadSeleccionada", comunidad_aux.getCOMUNIDAD()!= -1);
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
