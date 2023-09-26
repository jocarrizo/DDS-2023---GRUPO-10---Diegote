package presentation;

import domain.Comunidad;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import presentation.dto.MisDatos;

public class GetComunidadHandler implements Handler {

    @Override
    public void handle(@NotNull Context context) throws Exception {

        Long id = Long.valueOf(context.pathParamAsClass("idComunidad", Integer.class).get());

        Comunidad comunidad = this.comunidadPorId(id);

        if (comunidad != null) {
            MisDatos misDatos = new MisDatos();
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
