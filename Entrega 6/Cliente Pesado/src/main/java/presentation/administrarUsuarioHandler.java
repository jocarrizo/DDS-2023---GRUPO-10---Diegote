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

public class administrarUsuarioHandler implements Handler {

    String sql = "UPDATE Comunidad_X_Perfil SET esObservador = NOT esObservador, esAfectado = NOT esAfectado " +
            "WHERE id_perfil = :id_perfil AND id_comunidad = :id_comunidad";
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String requestBody = ctx.body();

        // Crear un ObjectMapper para manejar JSON (puedes tener esto como una instancia de clase o usar una inyección de dependencias)
        ObjectMapper objectMapper = new ObjectMapper();

        // Convertir el JSON a un Map<String, Boolean> usando Jackson
        Map<String, Boolean> data = objectMapper.readValue(requestBody, Map.class);

        String IDSESION = (String) data.get("IDSESION");

        // Eliminar IDSESION del mapa
        data.remove("IDSESION");

        // Crear un nuevo mapa para almacenar las claves con valores verdaderos
        Map<String, Boolean> rolesACambiar = new HashMap<>();

        // Filtrar las claves con valores verdaderos
        for (Map.Entry<String, Boolean> entry : data.entrySet()) {
            if (entry.getValue()) {
                rolesACambiar.put(entry.getKey(), true);
            }
        }

        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);
        //rolesACambiar.getKey() -> devuelve la comunidad donde tenemos que cambiar el rol del usuario
        try {

            for (Map.Entry<String, Boolean> entry : rolesACambiar.entrySet()) {
                Long comunidad = Long.valueOf(entry.getKey());
                Query query = em.createNativeQuery(sql);
                query.setParameter("id_perfil", IDSESION);
                query.setParameter("id_comunidad", comunidad);
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