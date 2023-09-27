package presentation;

import domain.Comunidad;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.HttpMethod;
import io.javalin.openapi.OpenApi;
import io.javalin.openapi.OpenApiParam;
import org.jetbrains.annotations.NotNull;
import presentation.dto.MisDatos;


public class GetComunidadHandler implements Handler {


    @OpenApi(
            path = "/api/comunidad/{id}",
            methods = {HttpMethod.GET},
            pathParams = @OpenApiParam(name = "id", description = "ID comunidad a buscar", required = true, type = Integer.class)
//            responses = {
//                    @OpenApiResponse(status = "200", content = @OpenApiContent(from = Mascota.class)),
//                    @OpenApiResponse(status = "404" )
//            }
    )
    @Override
    public void handle(@NotNull Context context) throws Exception {

        Long id = context.pathParamAsClass("idComunidad", Long.class).get();

        Comunidad comunidad = this.comunidadPorId(id);

        if (comunidad != null) {
            MisDatos misDatos = new MisDatos();
            misDatos.setTipo("Comunidad");
            misDatos.setId(id);
            misDatos.setCategoria(comunidad.getCategoria());
            misDatos.setPuntaje(comunidad.getPuntaje());
            context.status(200).json(misDatos);
        }
        context.status(404);

    }

    private Comunidad comunidadPorId(Long id){
        //SELECT id_comunidad, puntaje, categoria from Comunidad WHERE id_comunidad = id;
        return comunidad;
    }
}
