// NOTA : Puede que no vaya a funcionar porque el @OpenApi se ejecuta y configura preejecucion

/*
package presentation;

import domain.Confianza;
import example.hibernate.utils.BDUtils;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


public abstract class GetEntityHandler<T> implements Handler {
    //Dejamos variable global en caso de necesitar agregar funciones a futuro.
    private String hql;
    private final Class<T> entityClass;
    private final String pathParameterName;
    private Long id;
    private Confianza confianza;
    private Double puntaje;

    public GetEntityHandler(Class<T> entityClass, String pathParameterName) {
        this.entityClass = entityClass;
        this.pathParameterName = pathParameterName;
    }

    @OpenApi(
            path = "/api/pathParameterName/{id}",
            methods = {HttpMethod.GET},
            pathParams = {
                    @OpenApiParam(name = "id", description = "ID de la entidad a buscar", required = true, type = Long.class)
            }
    )
    @Override
    public void handle(@NotNull Context context) {
        Long id = context.pathParamAsClass("id", Long.class).get();

        T entity = entityById(id);

        if (entity != null) {
            context.status(200).json(entity);
        } else {
            context.status(404);
        }
    }


    private T entityById(Long id_obj){
        EntityManager em = BDUtils.getEntityManager();

        hql = "SELECT x FROM domain." + pathParameterName + " x WHERE x.id_"+ pathParameterName +" = ?1";

        TypedQuery<T> query = em.createQuery(hql, entityClass);
        query.setParameter(1, id_obj);

        List<T> entities = query.getResultList();

        em.close();
        return entities.get(0);
    }

    public Confianza getCategoria(T entity) {
        return confianza;
    }

    public Double getPuntaje(T entity){
        return puntaje;
    }
}




public class GetPerfilHandler extends GetEntityHandler<Perfil> {
    public GetPerfilHandler() {
        super(Perfil.class, "perfil");
    }
}



public class GetComunidadHandler extends GetEntityHandler<Comunidad> {
    public GetComunidadHandler() {
        super(Comunidad.class, "comunidad");
    }
}
*/
