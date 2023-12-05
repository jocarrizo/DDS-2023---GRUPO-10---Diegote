package persistance;

import domain.Servicios.Incidente;
import domain.Usuarios.Comunidades.Comunidad;
import domain.Usuarios.Confianza;
import domain.Usuarios.Perfil;
import lombok.SneakyThrows;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;


import example.hibernate.utils.BDUtils;

public class Actualizador implements Job {

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);


        try {
            List<Incidente> incidentes = getIncidentes(em);
            List<Perfil> perfiles = getPerfiles(em, incidentes);

            for (Perfil perfil : perfiles) {
                perfil.actualizarPuntaje();
                actualizarPerfilDB(em, perfil.getPuntaje(), perfil.getConfianza(), perfil.getId_perfil());
            }
            
            List<Comunidad> comunidades = getComunidades(em);

            for (Comunidad comunidad : comunidades){
                comunidad.actualizarPuntaje();
                comunidad.actualizarConfianza();
                actualizarComunidadDB(em, comunidad.getPuntaje(), comunidad.getConfianza(), comunidad.getId_comunidad());

            }


            BDUtils.commit(em);

        } catch (Exception e) {
            BDUtils.rollback(em);
            e.printStackTrace();
        }
        em.close();
    }

    public void actualizarComunidadDB(EntityManager em, Double nuevoPuntaje, Confianza nuevaCategoria, Long id){
        String sql = "UPDATE Comunidad c SET c.puntaje = :nuevoPuntaje, c.confianza = :nuevaCategoria WHERE c.id_comunidad = :id";
        em.createQuery(sql)
                .setParameter("nuevoPuntaje", nuevoPuntaje)
                .setParameter("nuevaCategoria", nuevaCategoria)
                .setParameter("id", id)
                .executeUpdate();
    }

    public void actualizarPerfilDB(EntityManager em, Double nuevoPuntaje, Confianza nuevaCategoria, Long id){
        String sql = "UPDATE Perfil t SET t.puntaje = :nuevoPuntaje, t.confianza = :nuevaCategoria WHERE t.id_perfil = :id";
        em.createQuery(sql)
            .setParameter("nuevoPuntaje", nuevoPuntaje)
            .setParameter("nuevaCategoria", nuevaCategoria)
            .setParameter("id", id)
            .executeUpdate();
    }

    public List<Perfil> getPerfiles(EntityManager em, List<Incidente> incidentes){

        List<Long> listaPerfiles = new ArrayList<>();

        for (Incidente incidente : incidentes) {
            listaPerfiles.add(incidente.getId_perfil_apertura().getId_perfil());
            listaPerfiles.add(incidente.getId_perfil_cierre().getId_perfil());
        }

        String hql = "SELECT p FROM Perfil p " +
                "WHERE p.id_perfil in :listaPerfiles";

        List<Perfil> perfiles =
                em.createQuery(hql, Perfil.class).setParameter("listaPerfiles",listaPerfiles)
                        .getResultList();


        for (Perfil perfil: perfiles){
            Long id_perfil = perfil.getId_perfil();

            List<Incidente> incidentesFiltrados = new ArrayList<>();
            for (Incidente incidente : incidentes) {
                if (incidente.getId_perfil_apertura().getId_perfil() == id_perfil || incidente.getId_perfil_apertura().getId_perfil() == id_perfil) {
                    incidentesFiltrados.add(incidente);
                }
            }

            perfil.setIncidentes(incidentesFiltrados);
        }

        return perfiles;
    }

    public List<Incidente> getIncidentes(EntityManager em) {

        List<Incidente> res = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -((calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7));
        Date ultimoDomingo = calendar.getTime();


        String hql = "SELECT i FROM Incidente i WHERE i.apertura >= ?1 AND (id_perfil_apertura is not null or id_perfil_cierre is not null)";

        List<Incidente> resultados =
                em.createQuery(hql, Incidente.class).setParameter(1,ultimoDomingo).
                getResultList();

        return resultados;
    }

    public List<Comunidad> getComunidades(EntityManager em) {

        String hql = "SELECT c FROM Comunidad c";

        return em.createQuery(hql, Comunidad.class).getResultList();

    }
}


