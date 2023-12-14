package presentation.GET;

import example.hibernate.utils.BDUtils;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.HttpMethod;
import io.javalin.openapi.OpenApi;
import org.jetbrains.annotations.NotNull;
import presentation.dto.DatosIncidente;
import presentation.dto.MisDatos;

import javax.persistence.EntityManager;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GetIncidentesHandler implements Handler {

    @OpenApi(
            path = "/api/Incidentes",
            methods = {HttpMethod.GET}
    )
    @Override
    public void handle(@NotNull Context context) throws Exception {
        EntityManager em = BDUtils.getEntityManager();
        List<DatosIncidente> incidentes ;
        try {
            incidentes = em.createQuery("SELECT NEW presentation.dto.DatosIncidente(i.id_incidente, i.monitoreable.id_monitoreable, i.entidad.id_entidad, m.nombre, e.nombre, i.apertura ) FROM Incidente i " +
                    "JOIN Monitoreable m ON i.monitoreable.id_monitoreable = m.id_monitoreable " +
                    "JOIN Entidad e ON e.id_entidad = i.entidad.id_entidad " +
                    "WHERE i.cierre IS NULL", DatosIncidente.class).getResultList();
            Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (DatosIncidente incidente : incidentes) {
                Date apertura = incidente.getApertura();
                String aperturaFormateada = formatter.format(apertura);
                incidente.setApertura_aux(aperturaFormateada);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            context.status(500);//TODO: VER ERROR CORRECTO
            return;
        }

        em.close();
        context.json(incidentes);
    }

}
