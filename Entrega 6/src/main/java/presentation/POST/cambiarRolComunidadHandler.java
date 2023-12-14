package presentation.POST;

import domain.Usuarios.Usuario;
import example.hibernate.utils.BDUtils;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import presentation.dto.CambiarRolRequest;

import javax.persistence.EntityManager;

public class cambiarRolComunidadHandler implements Handler {
    public void handle(@NotNull Context context) throws Exception {
        CambiarRolRequest solicitud = context.bodyAsClass(CambiarRolRequest.class);

        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);

        try {
            em.createQuery("UPDATE comunidad_x_perfil cxp SET cxp.esObservador = ?1 " +
                            "where cxp.perfil.id_perfil = ?2 and cxp.comunidad.id_comunidad = ?3")
                    .setParameter(1,solicitud.isEsObservador())
                    .setParameter(2,solicitud.getIdPerfil())
                    .setParameter(3,solicitud.getIdComunidad())
                    .executeUpdate();
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
