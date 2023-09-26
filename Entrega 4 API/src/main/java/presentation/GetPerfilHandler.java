package presentation;

import domain.Perfil;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import presentation.dto.MisDatos;


public class GetPerfilHandler implements Handler {

    @OpenApi(
            path = "/api/perfil/{id}",
            methods = {HttpMethod.GET},
            pathParams = @OpenApiParam(name = "id", description = "ID perfil a buscar", required = true, type = Long.class)
//            responses = {
//                    @OpenApiResponse(status = "200", content = @OpenApiContent(from = Mascota.class)),
//                    @OpenApiResponse(status = "404" )
//            }
    )
    @Override
    public void handle(@NotNull Context context) throws Exception {

        Long id = context.pathParamAsClass("idPerfil", Long.class).get();

        Perfil perfil = this.perfilPorId(id);

        if (perfil != null) {
            MisDatos misDatos = new MisDatos();
            misDatos.setId(id);
            misDatos.setCategoria(perfil.getCategoria());
            misDatos.setPuntaje(perfil.getPuntaje());
            context.status(200).json(misDatos);
        }
        context.status(404);

    }

    private Perfil perfilPorId(Long id){
        //SELECT id_perfil, puntaje, categoria from Perfil WHERE id_perfil = id;
        return perfil;
    }
}

