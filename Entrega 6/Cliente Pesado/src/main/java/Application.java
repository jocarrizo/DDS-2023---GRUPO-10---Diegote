import io.javalin.Javalin;
import io.javalin.openapi.plugin.OpenApiConfiguration;
import io.javalin.openapi.plugin.OpenApiPlugin;
import io.javalin.openapi.plugin.swagger.SwaggerConfiguration;
import io.javalin.openapi.plugin.swagger.SwaggerPlugin;
import org.quartz.*;

import persistance.Programador;
import presentation.GET.*;
import presentation.POST.LoginHandler;
import presentation.POST.PostAbrirIncidenteHandler;
import presentation.POST.PostCerrarIncidenteHandler;
import presentation.POST.cambiarRolComunidadHandler;

public class Application {

    public static void main(String[] args) throws SchedulerException {

        Javalin app = Javalin.create(config -> {
                    config.plugins.register(new OpenApiPlugin(new OpenApiConfiguration()));
                    config.plugins.register(new SwaggerPlugin(new SwaggerConfiguration()));
                    config.plugins.enableCors(cors -> {
                        cors.add(it-> it.anyHost());
                    });
                    config.staticFiles.add("/public");
                })
                .get("/", ctx -> {
                    ctx.redirect("/login.html");
                })
                .start(4567);
        System.out.println("Check out Swagger UI docs at http://localhost:4567/swagger");



        app.get("/api/perfil/{id}",     new GetPerfilHandler());
        app.get("/api/comunidad/{id}",  new GetComunidadHandler());
        app.get("/api/Entidades", new GetEntidadesHandler());
        app.get("/api/Establecimientos/{id}", new GetEstablecimientosPorEntidadHandler());
        app.get("/api/Servicios/{id}",new GetServiciosPorEstablecimiento());
        app.get("/api/Incidentes", new GetIncidentesHandler());

        app.post("/api/cambiarRolComunidad",new cambiarRolComunidadHandler());
        app.post("/api/login", new LoginHandler());
        app.post("/api/cerrarIncidente", new PostCerrarIncidenteHandler());
        app.post("/api/abrirIncidente", new PostAbrirIncidenteHandler());


        Programador.programar();
    }
}
/*
 INSERT INTO `prueba_api`.`perfil`
        (`id_perfil`,
        `confianza`,
        `puntaje`)
        VALUES
        (1,
        'ConfiableNivel1',
        6.0),
        (4,
        'NoConfiable',
        2.0),
        (6,
        'ConfiableNivel2',
        9.0);

INSERT INTO `prueba_api`.`comunidad`
        (`id_comunidad`,
        `confianza`,
        `puntaje`)
        VALUES
        (5,
        'ConfiableNivel1',
        5.0),
        (2,
        'ConfiableNivel2',
        5.0),
        (3,
        'NoConfiable',
        5.0);


INSERT INTO `prueba_api`.`comunidad_x_perfil` (`comunidad_id`, `perfil_id`)
SELECT DISTINCT id_comunidad, id_perfil
FROM (
	SELECT id_comunidad , id_perfil
	FROM comunidad
	CROSS JOIN perfil
	WHERE (id_comunidad, id_perfil) NOT IN (
		SELECT comunidad_id, perfil_id
		FROM `comunidad_x_perfil`
	)
	ORDER BY RAND()
	LIMIT 1
) AS tmp;

INSERT INTO `prueba_api`.`incidente`
	(`id_incidente`, `apertura`, `cierre`, `observaciones`, `id_perfil_apertura`, `id_perfil_cierre`)
VALUES
	(ROUND(RAND() * 99 + 1), -- id_incidente aleatorio entre 1 y 100
	 NOW() - INTERVAL FLOOR(RAND() * 7) DAY, -- Fecha de apertura aleatoria en los últimos 30 días
	 NOW() - INTERVAL FLOOR(RAND() * 2) DAY, -- Fecha de cierre aleatoria en los últimos 10 días
	 'Observaciones aleatorias', -- Observaciones fijas o aleatorias según lo desees
	 FLOOR(RAND() * 5 + 1), -- id_perfil_apertura aleatorio entre 1 y 5
	 FLOOR(RAND() * 5 + 1) -- id_perfil_cierre aleatorio entre 1 y 5
	);

*/
