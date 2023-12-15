import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import io.javalin.http.servlet.JavalinServletContext;
import io.javalin.openapi.plugin.OpenApiConfiguration;
import io.javalin.openapi.plugin.OpenApiPlugin;
import io.javalin.openapi.plugin.swagger.SwaggerConfiguration;
import io.javalin.openapi.plugin.swagger.SwaggerPlugin;
import io.javalin.rendering.JavalinRenderer;
import org.quartz.*;
import presentation.*;

import persistance.Programador;
import presentation.CargaMasivaHandler;
import presentation.controller.*;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.IOException;


public class Application {
    public static void main(String[] args) throws SchedulerException {
        // Ruta al archivo JSON de configuración descargado
        String pathToFirebaseConfig = "path/to/your/firebase-config.json";
/*
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new FileInputStream(pathToFirebaseConfig)))
                    .setDatabaseUrl("https://your-firebase-database-url.firebaseio.com%22/)
                            .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        //Inicializo el motor de templates
        initTemplateEngine();

        Javalin app = Javalin.create(config -> {
                    config.plugins.register(new OpenApiPlugin(new OpenApiConfiguration()));
                    config.plugins.register(new SwaggerPlugin(new SwaggerConfiguration()));
                    config.plugins.enableCors(cors -> {
                        cors.add(it-> it.anyHost());
                    });
                    config.staticFiles.add("/public");
                }).start(4567);

        app.get("/", ctx -> {
            ctx.attribute("key", "value");
            ctx.redirect("/login.html");
        });


        System.out.println("Check out Swagger UI docs at http://localhost:4567/swagger");


        app.get("/api/perfil/{id}",     new GetPerfilHandler());
        app.get("/api/comunidad/{id}",  new GetComunidadHandler());
        app.get("api/Entidades", new GetEntidadesHandler());
        app.get("api/Establecimientos/{id}", new GetEstablecimientosPorEntidadHandler());
        app.get("api/Servicios/{id}",new GetServiciosPorEstablecimiento());


        app.get("/cargaMasivaLiviano", ctx ->{
           ctx.redirect("/cargaMasivaLiviano.html");
        });
        app.post("/cargaMasivaLiviano/cargar", new CargaMasivaHandler());


        app.get("/administrarUsuarioLiviano", new administrarUsuarioController());
        app.post("/administrarUsuarioLivianoAplicar", new administrarUsuarioHandler()); //Ni yo me acuerdo que hice aca



        app.post("/cookies/incidentesLiviano/comunidad", ctx -> {
            ctx.cookie("COMUNIDAD_INCIDENTE",ctx.body());
        });
        app.post("/cookies/incidentesLiviano/filtro", ctx -> {
            ctx.cookie("FILTRO_INCIDENTE",ctx.body());
        });
        app.get("/incidentesLiviano", new incidentesPorEstadoController());
        app.get("/incidentesLiviano/tabla", new incidentesPorEstadoTablaController());


        app.get("/rankingsLiviano", new RankingsController());

        app.post("/api/login", new LoginHandler());


        Programador.programar();
    }

    private static void initTemplateEngine() {
        //Setea el motor de Templates para renderizar los archivo de texto

        JavalinRenderer.register(
                (path, model, context) -> { // Función que renderiza el template
                    Handlebars handlebars = new Handlebars();
                    Template template = null;
                    try {
                        template = handlebars.compile(
                                "public/" + path.replace(".hbs", ""));
                        return template.apply(model);
                    } catch (IOException e) {
                        e.printStackTrace();
                        context.status(HttpStatus.NOT_FOUND);
                        return "No se encuentra la página indicada...";
                    }
                }, ".hbs" // Extensión del archivo de template
        );
    }


}