/*
package presentation;

import domain.Comunidad;
import domain.Confianza;
import domain.Perfil;
import example.hibernate.utils.BDUtils;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.*;
import org.eclipse.jetty.server.session.Session;
import org.jetbrains.annotations.NotNull;
import presentation.dto.MisDatos;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


public abstract class GetEntityHandler<T> implements Handler {
    private String hql;
    private final Class<T> entityClass;
    private final String pathParameterName;

    public GetEntityHandler(Class<T> entityClass, String pathParameterName) {
        this.entityClass = entityClass;
        this.pathParameterName = pathParameterName;
    }

    @OpenApi(
            path = "/api/{entityName}/{id}",
            methods = {HttpMethod.GET},
            pathParams = {
                    @OpenApiParam(name = "entityName", description = "Nombre de la entidad a buscar", required = true, type = String.class),
                    @OpenApiParam(name = "id", description = "ID de la entidad a buscar", required = true, type = Long.class)
            }
    )
    @Override
    public void handle(@NotNull Context context) throws Exception {
        String entityName = context.pathParam("entityName");
        Long id = context.pathParamAsClass("id", Long.class).get();

        T entity = entityById(id);

        if (entity != null) {
            MisDatos misDatos = new MisDatos();
            misDatos.setId(id);
            misDatos.setCategoria(getCategoria(entity));
            misDatos.setPuntaje(getPuntaje(entity));
            context.status(200).json(misDatos);
        } else {
            context.status(404);
        }
    }


    private T entityById(Long id){
        EntityManager em = BDUtils.getEntityManager();

        TypedQuery<T> query = em.createQuery(hql, T.class);
        query.setParameter(1, id);

        List<T> entities = query.getResultList();

        em.close();
        return entities.get(0);
    }

    protected abstract Confianza getCategoria(T entity);

    protected abstract Double getPuntaje(T entity);
}


class GetPerfilHandler extends GetEntityHandler<Perfil> {

    private String hql = "SELECT p FROM domain.Perfil p WHERE p.id_perfil = ?1";

    public GetPerfilHandler() {
        super(Perfil.class, "perfil");
    }


    @Override
    protected Confianza getCategoria(Perfil entity) {
        return entity.getConfianza();
    }

    @Override
    protected Double getPuntaje(Perfil entity) {
        return entity.getPuntaje();
    }
}

class GetComunidadHandler extends GetEntityHandler<Comunidad> {

    private String hql = "SELECT c FROM domain.Comunidad c WHERE c.id_comunidad = ?1";

    public GetComunidadHandler() {
        super(Comunidad.class, "comunidad");
    }


    @Override
    protected Confianza getCategoria(Comunidad entity) {
        return entity.getConfianza();
    }

    @Override
    protected Double getPuntaje(Comunidad entity) {
        return entity.getPuntaje();
    }
}
*/



