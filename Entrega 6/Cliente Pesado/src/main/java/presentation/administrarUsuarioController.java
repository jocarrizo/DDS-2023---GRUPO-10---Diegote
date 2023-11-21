package presentation;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Usuarios.Comunidades.Comunidad;
import example.hibernate.utils.BDUtils;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import domain.Usuarios.Comunidades.comunidad_x_perfil;
import presentation.dto.RolComunidad;

public class administrarUsuarioController implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        String IDSESION = ctx.body();

        List<RolComunidad> roles = null;

        List<comunidad_x_perfil> comunidades = comunidad_x_perfilPorId(IDSESION);
        for (comunidad_x_perfil comunidad : comunidades){
            roles.add(new RolComunidad(comunidad.getComunidad().getId_comunidad(), comunidad.isEsAfectado()));
        }


        Map<String, Object> model = new HashMap<>();
        model.put("comunidades", roles);
        ctx.render("administrarUsuario.hbs", model);
    }

    private List<comunidad_x_perfil> comunidad_x_perfilPorId(Long id){
        EntityManager em = BDUtils.getEntityManager();

        //PEDIR AYUDA A JOACO, EL FILTRO ESTA MAL Y DESPUES NO SE SI LA QUERY ESTA BIEN
        String hql = "SELECT c FROM comunidad_x_perfil c WHERE c.comunidad = ?1";

        TypedQuery<comunidad_x_perfil> query = em.createQuery(hql, comunidad_x_perfil.class);
        query.setParameter(1, id);

        List<comunidad_x_perfil> result = query.getResultList();

        em.close();

        return result;
    }
}
