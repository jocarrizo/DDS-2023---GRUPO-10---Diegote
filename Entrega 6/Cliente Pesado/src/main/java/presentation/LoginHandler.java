package presentation;

import domain.Usuarios.Usuario;
import example.hibernate.utils.BDUtils;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.HttpMethod;
import io.javalin.openapi.OpenApi;
import io.javalin.openapi.OpenApiParam;
import org.jetbrains.annotations.NotNull;
import presentation.dto.LoginRequest;
import presentation.dto.LoginResponse;

import javax.persistence.EntityManager;
import java.util.Date;

public class LoginHandler implements Handler {

    @Override
    public void handle(@NotNull Context context) throws Exception {
        //validamos user/pass y buscamos datos de ese usuario para agregar en la sesión

        LoginRequest loginRequest = context.bodyAsClass(LoginRequest.class);

        EntityManager em = BDUtils.getEntityManager();

        //TODO: TIENE QUE SER UN USUARIO STRING NO ID AUTOGENERADO
        Usuario results = new Usuario();
        try {
            results = em.createQuery("SELECT t FROM Usuario t where t.correo = ?1", Usuario.class)
                    .setParameter(1, loginRequest.getUsername()).getSingleResult();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            context.status(401);
            return;
        }

        em.close();
        SessionManager sesionManager = SessionManager.get();
        String idSesion = sesionManager.crearSession("usuario", results);

        sesionManager.agregarAtributo(idSesion, "fechaInicio", new Date());
        sesionManager.agregarAtributo(idSesion, "rol", results.getId_usuario());
        System.out.println("Logueando usuario:" + results.getId_usuario());

        context.cookie("IDUSUARIO", String.valueOf(results.getId_usuario()));
        context.json(new LoginResponse(idSesion, results.getId_usuario()));
    }

}
