package presentation;

import example.hibernate.utils.BDUtils;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.*;
import org.jetbrains.annotations.NotNull;
import presentation.dto.MisDatos;

import javax.persistence.EntityManager;
import javax.persistence.Query;


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

        Long id = context.pathParamAsClass("idComunidad", Long.class).get();

        MisDatos comunidad = this.comunidadPorId(id);

        if (comunidad != null) {
            MisDatos misDatos = new MisDatos();
            misDatos.setId(id);
            misDatos.setCategoria(comunidad.getCategoria());
            misDatos.setPuntaje(comunidad.getPuntaje());
            context.status(200).json(misDatos);
        }
        context.status(404);

    }

    private MisDatos comunidadPorId(Long id){

        EntityManager em = BDUtils.getEntityManager();

        String hql = "SELECT c.id_comunidad, c.puntaje FROM Comunidad c WHERE c.id_comunidad = ?1";

        Query query = em.createQuery(hql, MisDatos.class);
        query.setParameter(1, id);

        MisDatos comunidad = (MisDatos) query.getSingleResult();

        em.close();
        return comunidad;
    }
}
