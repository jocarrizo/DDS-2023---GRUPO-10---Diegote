package presentation;

import domain.Comunidad;
import example.hibernate.utils.BDUtils;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


public class GetComunidadHandler implements Handler {

    @OpenApi(
            path = "/api/comunidad/{id}",
            methods = {HttpMethod.GET},
            pathParams = @OpenApiParam(name = "id", description = "ID comunidad a buscar", required = true, type = Integer.class)
    )
    @Override
    public void handle(@NotNull Context context) {

        Long id = context.pathParamAsClass("id", Long.class).get();

        Comunidad comunidad = this.comunidadPorId(id);

        if (comunidad != null) {
            context.status(200).json(comunidad);
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

/*
public class GetComunidadHandler extends GetEntityHandler<Comunidad> {
    public GetComunidadHandler() {
        super(Comunidad.class, "comunidad");
    }
}
*/