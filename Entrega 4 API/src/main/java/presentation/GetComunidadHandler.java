package presentation;

import domain.Comunidad;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.HttpMethod;
import io.javalin.openapi.OpenApi;
import io.javalin.openapi.OpenApiParam;
import io.javalin.openapi.OpenApiResponse;
import org.eclipse.jetty.server.session.Session;
import org.jetbrains.annotations.NotNull;
import presentation.dto.MisDatos;

import javax.persistence.Query;


public class GetComunidadHandler implements Handler {


    @OpenApi(
            path = "/api/comunidad/{id}",
            methods = {HttpMethod.GET},
            pathParams = @OpenApiParam(name = "id", description = "ID comunidad a buscar", required = true, type = Integer.class)
            responses = {
                    @OpenApiResponse(status = "200", content = @OpenApiContent(from = MisDatos.class)),
                    @OpenApiResponse(status = "404" )
            }
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
        Session session = HibernateUtil.getSessionFactory().openSession();
        //SELECT id_perfil, puntaje, categoria from Perfil WHERE id_perfil = id;
        String hql = "SELECT id_comunidad, categoria, puntaje FROM Comunidad WHERE id_comundad = :id";

        Query query = session.createQuery(hql, MisDatos.class);
        query.setParameter("id", id);

        MisDatos comunidad = query.uniqueResult();

        session.close();
        return comunidad;
    }
}
