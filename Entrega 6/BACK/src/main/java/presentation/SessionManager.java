package presentation;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SessionManager {

    private static SessionManager instancia;

    private Map<String, Map<String, Object>> sesiones;

    private SessionManager() {
        this.sesiones = new HashMap<>();
    }

    public static SessionManager get() {
        if (instancia == null) {
            instancia = new SessionManager();
        }
        return instancia;
    }

    public String crearSession() {
        return this.crearSession(new HashMap<>());
    }

    public String crearSession(String clave, Object valor) {
        HashMap<String, Object> atributo = new HashMap<>();
        atributo.put(clave, valor);
        return this.crearSession(atributo);
    }

    public String crearSession(Map<String, Object> atributos) {
        String id = UUID.randomUUID().toString();
        this.sesiones.put(id, atributos);
        return id;
    }

    public Map<String, Object> obtenerAtributos(String id) {
        return this.sesiones.get(id);
    }

    public void agregarAtributo(String id, String clave, Object valor) {
        Map<String, Object> atributos = this.sesiones.get(id);
        atributos.put(clave, valor);
    }

    public void agregarAtributos(String id, Map<String, Object> nuevosAtributos) {
        Map<String, Object> atributos = this.sesiones.get(id);
        atributos.putAll(nuevosAtributos);
    }

    public Map<String, Object> eliminar(String id) {
        //esto no elimina la cookie del frontend
        return this.sesiones.remove(id);
    }

}