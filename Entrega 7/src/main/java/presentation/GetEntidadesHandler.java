package presentation;

import example.hibernate.utils.BDUtils;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.HttpMethod;
import io.javalin.openapi.OpenApi;
import org.jetbrains.annotations.NotNull;
import presentation.dto.MisDatos;

import javax.persistence.EntityManager;
import java.util.List;

public class GetEntidadesHandler implements Handler {
    @OpenApi(
            path = "/api/Entidades",
            methods = {HttpMethod.GET}
    )
    @Override
    public void handle(@NotNull Context context) throws Exception {
        EntityManager em = BDUtils.getEntityManager();
        List<MisDatos> entidades ;
        try {
            entidades = em.createQuery("SELECT NEW presentation.dto.MisDatos(t.id_entidad,t.nombre) FROM Entidad t ", MisDatos.class).getResultList();

        }catch(Exception e){
            System.out.println(e.getMessage());
            context.status(500);
            return;
        }

        em.close();
        context.json(entidades);
    }
}
