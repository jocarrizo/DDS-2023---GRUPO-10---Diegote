

import io.javalin.Javalin;
import org.quartz.*;
import presentation.GetComunidadHandler;
import presentation.GetPerfilHandler;

import persistance.Programador;

public class Application {

    public static void main(String[] args) throws SchedulerException {
        Javalin app = Javalin.create()
                .get("/", ctx -> ctx.result("Hello World"))
                .start(4567);
        System.out.println("Check out Swagger UI docs at http://localhost:4567/swagger");

        app.get("/api/perfil{id}", new GetPerfilHandler());
        app.get("/api/comunidad{id}", new GetComunidadHandler());

        Programador.programar();
    }

}