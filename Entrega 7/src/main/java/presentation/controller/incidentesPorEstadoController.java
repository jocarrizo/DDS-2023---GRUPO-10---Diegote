package presentation.controller;

import domain.Usuarios.Comunidades.Comunidad;
import example.hibernate.utils.BDUtils;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import presentation.dto.IncidentesView;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;

public class incidentesPorEstadoController implements Handler{

    String hql_comunidad = "SELECT c.comunidad FROM comunidad_x_perfil c WHERE c.perfil.id_perfil = :perfilito";
    @Override
    public void handle(@NotNull Context ctx) throws Exception{

        String idUsuarioCookie = ctx.cookie("IDUSUARIO");

        Long IDUSUARIO = (idUsuarioCookie != null) ? Long.valueOf(idUsuarioCookie) : null;

        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);

        try {
            Query query_comunidad = em.createQuery(hql_comunidad);
            query_comunidad.setParameter("perfilito", IDUSUARIO);
            List<Comunidad> comunidades = query_comunidad.getResultList();


            BDUtils.commit(em);
            ctx.status(200);

            Map<String, Object> model = new HashMap<>();
            model.put("comunidad", comunidades);

            ctx.render("incidentesLiviano.hbs", model);

        } catch (Exception e) {
            BDUtils.rollback(em);
            e.printStackTrace();
            ctx.status(400).result("Hubo un error en la carga de la página");
        } finally {
            em.close();
        }



    }

}
