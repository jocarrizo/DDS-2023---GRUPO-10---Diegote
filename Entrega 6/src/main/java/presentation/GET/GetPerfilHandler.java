package presentation.GET;

import domain.Usuarios.Perfil;
import example.hibernate.utils.BDUtils;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.*;
import org.jetbrains.annotations.NotNull;
import presentation.dto.MisDatos;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


public class GetPerfilHandler implements Handler {

    @OpenApi(
            path = "/api/perfil/{id}",
            methods = {HttpMethod.GET},
            pathParams = @OpenApiParam(name = "id", description = "ID perfil a buscar", required = true, type = Long.class)
    )
    @Override
    public void handle(@NotNull Context context) throws Exception {

        Long id = context.pathParamAsClass("id", Long.class).get();

        Perfil perfil = perfilPorId(id);

        if (perfil != null) {
            MisDatos misDatos = new MisDatos();
            misDatos.setId(id);
            misDatos.setCategoria(perfil.getConfianza());
            misDatos.setPuntaje(perfil.getPuntaje());
            context.status(200).json(misDatos);
        }else context.status(404);
    }

    public Perfil perfilPorId(Long id) {
        EntityManager em = BDUtils.getEntityManager();

        String hql = "SELECT p FROM Perfil p WHERE p.id_perfil = ?1";

        TypedQuery<Perfil> query = em.createQuery(hql, Perfil.class);
        query.setParameter(1, id);

        List<Perfil> perfiles = query.getResultList();

        em.close();
        return perfiles.get(0);
    }
}

