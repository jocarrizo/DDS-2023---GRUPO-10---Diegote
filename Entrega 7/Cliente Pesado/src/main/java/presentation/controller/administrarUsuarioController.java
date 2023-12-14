package presentation.controller;

import java.util.*;

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

        Long IDUSUARIO = Long.valueOf(Objects.requireNonNull(ctx.cookie("IDUSUARIO")));

        List<RolComunidad> comunidades = new ArrayList<>();

        List<comunidad_x_perfil> comunidades_crudas = comunidad_x_perfilPorId(IDUSUARIO);
        for (comunidad_x_perfil comunidad : comunidades_crudas){
            comunidades.add(new RolComunidad(comunidad.getComunidad().getNombre(),comunidad.getComunidad().getId_comunidad(), comunidad.isEsAfectado(),comunidad.isEsAdmin()));
        }


        Map<String, Object> model = new HashMap<>();
        model.put("comunidad", comunidades);
        ctx.render("administrarUsuario.hbs", model);
    }

    private List<comunidad_x_perfil> comunidad_x_perfilPorId(Long id){
        EntityManager em = BDUtils.getEntityManager();

        String hql = "SELECT c FROM comunidad_x_perfil c WHERE c.perfil.id_perfil = :x";

        TypedQuery<comunidad_x_perfil> query = em.createQuery(hql, comunidad_x_perfil.class);
        query.setParameter("x", id);
        List<comunidad_x_perfil> result = query.getResultList();

        em.close();

        return result;
    }
}
