package presentation;

import domain.Perfil;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.*;
import org.eclipse.jetty.server.session.Session;
import org.jetbrains.annotations.NotNull;
import presentation.dto.MisDatos;

import javax.persistence.Query;


public class GetPerfilHandler implements Handler {

    @OpenApi(
            path = "/api/perfil/{id}",
            methods = {HttpMethod.GET},
            pathParams = @OpenApiParam(name = "id", description = "ID perfil a buscar", required = true, type = Long.class);
            responses = {
                    @OpenApiResponse(status = "200", content = @OpenApiContent(from = MisDatos.class)),
                    @OpenApiResponse(status = "404" )
            }
    )
    @Override
    public void handle(@NotNull Context context) throws Exception {

        Long id = context.pathParamAsClass("idPerfil", Long.class).get();

        MisDatos perfil = this.perfilPorId(id);

        if (perfil != null) {
            MisDatos misDatos = new MisDatos();
            misDatos.setId(id);
            misDatos.setCategoria(perfil.getCategoria());
            misDatos.setPuntaje(perfil.getPuntaje());
            context.status(200).json(misDatos);
        }
        context.status(404);

    }

    private MisDatos perfilPorId(Long id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        //SELECT id_perfil, puntaje, categoria from Perfil WHERE id_perfil = id;
        String hql = "SELECT id_perfil, categoria, puntaje FROM Perfil WHERE id_perfil = :id";

        Query query = session.createQuery(hql, MisDatos.class);
        query.setParameter("id", id);

        MisDatos perfil = query.uniqueResult();

        session.close();
        return perfil;
    }
}

