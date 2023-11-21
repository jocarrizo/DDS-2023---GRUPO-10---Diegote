package presentation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class administrarUsuarioController implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        String IDSESION = ctx.body();

        Collection<rolComunidad> comunidades = //Query comunidades segun IDSESION


        Map<String, Object> model = new HashMap<>();
        model.put("comunidades", comunidades);
        ctx.render("administrarUsuario.hbs", model);
    }
}
