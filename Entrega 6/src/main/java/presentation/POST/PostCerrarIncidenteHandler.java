package presentation.POST;

import domain.Usuarios.Usuario;
import example.hibernate.utils.BDUtils;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import presentation.SessionManager;
import presentation.dto.CerrarIncidenteRequest;
import presentation.dto.LoginRequest;
import presentation.dto.LoginResponse;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.Map;

public class PostCerrarIncidenteHandler implements Handler {

    @Override
    public void handle(@NotNull Context context) throws Exception {

        CerrarIncidenteRequest cerrarRequest = context.bodyAsClass(CerrarIncidenteRequest.class);

        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);

        try{
            em.createQuery("UPDATE Incidente t SET t.cierre = ?1, t.id_perfil_cierre.id_perfil = ?2 where t.id_incidente = ?3")
                    .setParameter(1, new Date())
                    .setParameter(3,cerrarRequest.getIdIncidente())
                    .setParameter(2, cerrarRequest.getIdPerfil())
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
