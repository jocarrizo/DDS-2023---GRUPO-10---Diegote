package persistance;

import domain.Servicios.Incidente;
import domain.Usuarios.Comunidades.Comunidad;
import domain.Usuarios.Perfil;
import example.hibernate.utils.BDUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.quartz.JobBuilder.newJob;

class ActualizadorTest {

    @Test
    @DisplayName("Actualizacion de puntajes")
    public void execute() {

        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);

        Actualizador act = new Actualizador();

        try {
            List<Incidente> incidentes = act.getIncidentes(em);
            List<Perfil> perfiles = act.getPerfiles(em, incidentes);

            for (Perfil perfil : perfiles) {
                perfil.actualizarPuntaje();
                act.actualizarPerfilDB(em, perfil.getPuntaje(), perfil.getConfianza(), perfil.getId_perfil());
            }

            List<Comunidad> comunidades = act.getComunidades(em);
            for (Comunidad comunidad : comunidades){
                comunidad.actualizarPuntaje();
                comunidad.actualizarConfianza();
                act.actualizarComunidadDB(em, comunidad.getPuntaje(), comunidad.getConfianza(), comunidad.getId_comunidad());
            }


            BDUtils.commit(em);

        } catch (Exception e) {
            BDUtils.rollback(em);
            System.out.println(e.getMessage());
        }
        em.close();
    }
}