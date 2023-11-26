package presentation.POST;

import domain.Entidades.Entidad;
import domain.Servicios.Incidente;
import domain.Servicios.Monitoreable;
import domain.Servicios.Servicio;
import domain.Usuarios.Comunidades.Comunidad;
import domain.Usuarios.Comunidades.comunidad_x_perfil;
import domain.Usuarios.Perfil;
import domain.Usuarios.Usuario;
import example.hibernate.utils.BDUtils;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import presentation.SessionManager;
import presentation.dto.AbrirIncidenteRequest;
import presentation.dto.CerrarIncidenteRequest;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PostAbrirIncidenteHandler implements Handler {

    @Override
    public void handle(@NotNull Context context) throws Exception {

        AbrirIncidenteRequest datos = context.bodyAsClass(AbrirIncidenteRequest.class);

        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);

        SessionManager sm = SessionManager.get();

        Map<String, Object> sesion = sm.obtenerAtributos(datos.getIdSesion());

        Usuario usuario = (Usuario) sesion.get("usuario");


        try {

            List<Comunidad> comunidades = em.createQuery("SELECT c FROM Comunidad c JOIN comunidad_x_perfil cxp ON c.id_comunidad = cxp.comunidad.id_comunidad AND cxp.perfil.id_perfil = ?1", Comunidad.class)
                    .setParameter(1, datos.getIdPerfil())
                    .getResultList();

            Monitoreable monitoreable = em.createQuery("SELECT c FROM Servicio c where c.id_monitoreable = ?1 ", Servicio.class)
                    .setParameter(1, datos.getIdServicio())
                    .getSingleResult();

            Entidad entidad = em.createQuery("SELECT c FROM Entidad c where c.id_entidad = ?1 ", Entidad.class)
                    .setParameter(1, datos.getIdEntidad())
                    .getSingleResult();

            Perfil perfil = em.createQuery("SELECT c FROM Perfil c WHERE c.id_perfil = ?1", Perfil.class)
                    .setParameter(1, datos.getIdPerfil())
                    .getSingleResult();


            for(Comunidad comunidad : comunidades) {
                Incidente incidente = new Incidente(datos.getObservaciones(),monitoreable,entidad,comunidad,perfil);
                em.persist(incidente);
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            context.status(401);
            BDUtils.rollback(em);
            em.close();
            return;
        }
        BDUtils.commit(em);
        em.close();

        context.status(201);
    }



}
