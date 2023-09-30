package persistance;

import domain.Confianza;
import domain.Incidente;
import domain.Perfil;

import lombok.SneakyThrows;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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

            actualizarComunidadDB(em);

            BDUtils.commit(em);

        } catch (Exception e) {
            BDUtils.rollback(em);
            e.printStackTrace();
        }
        em.close();
    }

    private void actualizarComunidadDB(EntityManager em){
        String sql = "UPDATE comunidad c " +
                "SET c.puntaje = (SELECT AVG(p.puntaje) FROM perfil p WHERE p.id_perfil = c.id_perfil)";
        em.createNativeQuery(sql).executeUpdate();
    }

    private void actualizarPerfilDB(EntityManager em, Double nuevoPuntaje, Confianza nuevaCategoria, Long id){
        String sql = "UPDATE Perfil t SET t.puntaje = :nuevoPuntaje, t.confianza = :nuevaCategoria WHERE t.id_perfil = :id";
        Query query = em.createQuery(sql);
        query.setParameter("nuevoPuntaje", nuevoPuntaje);
        query.setParameter("nuevaCategoria", nuevaCategoria);
        query.setParameter("id", id);

        query.executeUpdate();
    }

    private List<Perfil> getPerfiles(EntityManager em, List<Incidente> incidentes){

        List<Long> listaPerfiles = new ArrayList<>();

        for (Incidente incidente : incidentes) {
            listaPerfiles.add(incidente.getId_perfil_apertura().getId_perfil());
            listaPerfiles.add(incidente.getId_perfil_cierre().getId_perfil());
        }

        String hql = "SELECT p.id_perfil, p.confianza, p.puntaje, p.categoria FROM Perfil p " +
                "WHERE p.id_perfil in :listaPerfiles";

        Query query = em.createQuery(hql, Perfil.class);
        query.setParameter("listaPerfiles", listaPerfiles);

        System.out.println(query.getResultList());

        return query.getResultList();
    }

    private List<Incidente> getIncidentes(EntityManager em) {

        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime ultimoDomingo = ahora.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).withHour(13).withMinute(0).withSecond(0);

        String hql = "SELECT i.id_incidente, i.apertura, i.cierre, i.id_perfil_apertura, i.id_perfil_cierre FROM Incidente i WHERE i.apertura >= ?1";

        Query query = em.createQuery(hql, Incidente.class);
        query.setParameter(1, ultimoDomingo);

        return query.getResultList();
    }



}


