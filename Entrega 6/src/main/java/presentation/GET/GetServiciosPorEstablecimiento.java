package presentation.GET;

import domain.Entidades.Tramo;
import domain.Servicios.Servicio;
import example.hibernate.utils.BDUtils;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.HttpMethod;
import io.javalin.openapi.OpenApi;
import io.javalin.openapi.OpenApiParam;
import org.jetbrains.annotations.NotNull;
import presentation.dto.DatosServicio;
import presentation.dto.MisDatos;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class GetServiciosPorEstablecimiento implements Handler {
    @OpenApi(
            path = "/api/Servicios/{id}",
            methods = {HttpMethod.GET},
            pathParams = @OpenApiParam(name = "id", description = "Servicios de Entidad", required = true, type = Long.class)
    )
    @Override
    public void handle(@NotNull Context context) throws Exception {
        EntityManager em = BDUtils.getEntityManager();

        Long id = context.pathParamAsClass("id", Long.class).get();
        System.out.println(id);

        List<Tramo> tramos;
        List<DatosServicio> servicios = new ArrayList<DatosServicio>();

        try {
            tramos = em.createQuery("SELECT t FROM Tramo t where t.establecimiento.id_establecimiento = ?1", Tramo.class)
                        .setParameter(1,id)
                        .getResultList();

            for(Tramo tramo : tramos){
                for(Servicio servicio : tramo.obtenerServicios()){
                    servicios.add(new DatosServicio(servicio.getId_monitoreable(),servicio.getNombre()));
                }
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
            context.status(500);//TODO: VER ERROR CORRECTO
            em.close();
            return;
        }

        em.close();
        context.status(200);
        context.json(servicios);
    }
}
