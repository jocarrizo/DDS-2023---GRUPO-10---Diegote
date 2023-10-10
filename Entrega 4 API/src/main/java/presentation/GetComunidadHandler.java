/*
package presentation;

import domain.Comunidad;
import domain.Perfil;
import example.hibernate.utils.BDUtils;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.*;
import org.jetbrains.annotations.NotNull;
import presentation.dto.MisDatos;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


public class GetComunidadHandler implements Handler {

    @OpenApi(
            path = "/api/comunidad/{id}",
            methods = {HttpMethod.GET},
            pathParams = @OpenApiParam(name = "id", description = "ID comunidad a buscar", required = true, type = Integer.class)
            //responses = {
            //        @OpenApiResponse(status = "200", content = @OpenApiContent(from = MisDatos.class)),
    //        @OpenApiResponse(status = "404" )
            //       }
    )
    @Override
    public void handle(@NotNull Context context) throws Exception {

        Long id = context.pathParamAsClass("id", Long.class).get();

        Comunidad comunidad = this.comunidadPorId(id);

        if (comunidad != null) {
            MisDatos misDatos = new MisDatos();
            misDatos.setId(id);
            misDatos.setCategoria(comunidad.getConfianza());
            misDatos.setPuntaje(comunidad.getPuntaje());
            context.status(200).json(misDatos);
        }else context.status(404);

    }

    private Comunidad comunidadPorId(Long id){

        EntityManager em = BDUtils.getEntityManager();

        String hql = "SELECT c FROM domain.Comunidad c WHERE c.id_comunidad = ?1";

        TypedQuery<Comunidad> query = em.createQuery(hql, Comunidad.class);
        query.setParameter(1, id);

        List<Comunidad> comunidad = query.getResultList();

        BDUtils.commit(em);
        em.close();
        return comunidad.get(0);
    }
}
*/