//import com.example.javalin.presentacion.GetMascotaIdHandler;
//import com.example.javalin.presentacion.GetMascotasHandler;
//import com.example.javalin.presentacion.LoginHandler;
//import com.example.javalin.presentacion.PostMascotaHandler;


import presentation.GetComunidadHandler;
import presentation.GetPerfilHandler;


import io.javalin.Javalin;
import io.javalin.openapi.plugin.OpenApiConfiguration;
import io.javalin.openapi.plugin.OpenApiPlugin;
import io.javalin.openapi.plugin.swagger.SwaggerConfiguration;
import io.javalin.openapi.plugin.swagger.SwaggerPlugin;
public class Application {

    public static void main(String[] args) {
        Javalin app = Javalin.create()
                .get("/", ctx -> ctx.result("Hello World"))
                .start(4567);
        System.out.println("Check out Swagger UI docs at http://localhost:4567/swagger");

        app.get("/api/perfil{id}", new GetPerfilHandler());
        app.get("/api/comunidad{id}", new GetComunidadHandler());

        programador();
    }

    private static void programador() throws SchedulerException {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail job = newJob(Actualizador.class)
                .withIdentity("ActualizarDB", "group1")
                .build();
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("DispararActualizasao", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 13 * * SUN"))
                .forJob("ActualizarDB", "group1")
                .build();
        scheduler.start();

        scheduler.scheduleJob(job, trigger);

    }


}