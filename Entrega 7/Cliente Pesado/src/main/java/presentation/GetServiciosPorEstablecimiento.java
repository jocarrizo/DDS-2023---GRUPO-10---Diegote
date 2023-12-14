package presentation;

import domain.Entidades.Tramo;
import example.hibernate.utils.BDUtils;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.HttpMethod;
import io.javalin.openapi.OpenApi;
import io.javalin.openapi.OpenApiParam;
import org.jetbrains.annotations.NotNull;
import presentation.dto.MisDatos;

import javax.persistence.EntityManager;
import java.util.List;

public class GetServiciosPorEstablecimiento implements Handler {
    @OpenApi(
            path = "/api/Servicios/{id}",
            methods = {HttpMethod.GET},
            pathParams = @OpenApiParam(name = "id", description = "Servicios de Entidad", required = true, type = Long.class)
    )
    @Override
    public void handle(@NotNull Context context) throws Exception {
        EntityManager em = BDUtils.getEntityManager();

        Long id = context.pathParamAsClass("id", Long.class).get();
        System.out.println(id);

        List<Tramo> tramos;
        try {
            tramos = em.createQuery("SELECT t FROM Tramo t where t.establecimiento.id_establecimiento = ?1", Tramo.class)
                        .setParameter(1,id)
                        .getResultList();
            System.out.println(tramos);
        }catch(Exception e){
            System.out.println(e.getMessage());
            context.status(500);//TODO: VER ERROR CORRECTO
            em.close();
            return;
        }



        em.close();
        context.status(200);
    }
}
