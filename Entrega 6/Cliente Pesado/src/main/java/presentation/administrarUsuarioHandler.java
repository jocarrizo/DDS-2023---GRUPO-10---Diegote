package presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class administrarUsuarioHandler implements Handler {
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
        Map<String, Boolean> rolesVerdaderos = new HashMap<>();

        // Filtrar las claves con valores verdaderos
        for (Map.Entry<String, Boolean> entry : data.entrySet()) {
            if (entry.getValue()) {
                rolesVerdaderos.put(entry.getKey(), true);
            }
        }


        // UPDATE DE LOS ROLES DE LOS USUARIOS EN CADA COMUNIDAD

        // Enviar una respuesta al cliente
        ctx.status(200).result("Roles actualizados exitosamente"); // Puedes enviar la respuesta que desees al frontend
    }

    }
}
