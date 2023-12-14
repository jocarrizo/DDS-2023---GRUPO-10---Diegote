package presentation.GET;

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

public class GetEstablecimientosPorEntidadHandler implements Handler {
    @OpenApi(
            path = "/api/Establecimientos/{id}",
            methods = {HttpMethod.GET},
            pathParams = @OpenApiParam(name = "id", description = "Establecimientos de Entidad", required = true, type = Long.class)
    )
    @Override
    public void handle(@NotNull Context context) throws Exception {
        EntityManager em = BDUtils.getEntityManager();

        Long id = context.pathParamAsClass("id", Long.class).get();

        System.out.println(id);

        List<MisDatos> establecimientos ;
        try {
            establecimientos = em.createQuery("SELECT NEW presentation.dto.MisDatos(t.id_establecimiento,t.nombre) FROM Establecimiento t where t.entidad.id_entidad=?1", MisDatos.class)
                    .setParameter(1,id)
                    .getResultList();

        }catch(Exception e){
            System.out.println(e.getMessage());
            context.status(500);//TODO: VER ERROR CORRECTO
            return;
        }

        em.close();
        context.json(establecimientos);
    }
}

