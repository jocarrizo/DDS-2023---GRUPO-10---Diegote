package presentation.controller;

import domain.Usuarios.Comunidades.Comunidad;
import domain.Usuarios.Comunidades.comunidad_x_perfil;
import example.hibernate.utils.BDUtils;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import presentation.dto.IncidentesView;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;

public class incidentesPorEstadoTablaController implements Handler{

    @Override
    public void handle(@NotNull Context ctx) throws Exception{

        String hql_incidentes = "SELECT NEW presentation.dto.IncidentesView(i.id_incidente,i.observaciones,i.apertura,i.cierre, i.abierto) FROM Incidente i where i.comunidad.id_comunidad = :x";

        String comunidadIncidenteCookie = ctx.cookie("COMUNIDAD_INCIDENTE");
        Long COMUNIDAD_INCIDENTE = (comunidadIncidenteCookie != null) ? Long.valueOf(comunidadIncidenteCookie) : null;

        String FILTRO_INCIDENTE = ctx.cookie("FILTRO_INCIDENTE");


        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);

        try {


            if (Objects.equals(FILTRO_INCIDENTE, "Abierto")){
                    hql_incidentes = hql_incidentes + " AND i.abierto=true";
            } else if (Objects.equals(FILTRO_INCIDENTE, "Cerrado")) {
                    hql_incidentes = hql_incidentes + " AND i.abierto=false";
            }

            TypedQuery<IncidentesView> query = em.createQuery(hql_incidentes, IncidentesView.class);
            query.setParameter("x", COMUNIDAD_INCIDENTE);
            List<IncidentesView> incidentes = query.getResultList();

            BDUtils.commit(em);
            ctx.status(200);

            Map<String, Object> model = new HashMap<>();
            model.put("incidente", incidentes);
            ctx.render("incidentesLivianoTabla.hbs", model);

            ctx.removeCookie("COMUNIDAD_INCIDENTE");
            ctx.removeCookie("FILTRO_INCIDENTE");


        } catch (Exception e) {
            BDUtils.rollback(em);
            e.printStackTrace();
        } finally {
            em.close();
        }



    }

}
