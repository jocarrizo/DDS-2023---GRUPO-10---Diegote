package presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.hibernate.utils.BDUtils;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class administrarUsuarioHandler implements Handler {

    String sql = "UPDATE Comunidad_X_Perfil SET Observador = NOT Observador, Afectado = NOT Afectado " +
            "WHERE perfil_id = :idPerfil AND comunidad_id = :idComunidad";


    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        Long IDUSUARIO = Long.valueOf(Objects.requireNonNull(ctx.cookie("IDUSUARIO")));

        String[] comunidadesACambiar = ctx.bodyAsClass(String[].class);

        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);
        try {

            for (String comunidad : comunidadesACambiar) {
                Query query = em.createNativeQuery(sql);
                query.setParameter("idPerfil", IDUSUARIO);
                query.setParameter("idComunidad", Long.valueOf(comunidad));
                query.executeUpdate();
            }

            BDUtils.commit(em);
            ctx.status(200).result("Roles actualizados exitosamente"); // Puedes enviar la respuesta que desees al frontend

        } catch (Exception e) {
            BDUtils.rollback(em);
            e.printStackTrace();
        }
        em.close();

    }
}