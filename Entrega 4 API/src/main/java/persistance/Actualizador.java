package persistance;

import domain.*;

import jakarta.transaction.Transaction;
import lombok.SneakyThrows;
import org.eclipse.jetty.server.session.Session;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

public class Actualizador implements Job {

    private String update_query = "UPDATE :tabla SET puntaje = :nuevoPuntaje, categoria =:nuevaCategoria WHERE id_perfil = :entityId";

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;


        try {
            transaction = session.beginTransaction();

            List<Incidente> incidentes = getIncidentes(session);
            List<Perfil> perfiles = getPerfiles(session, incidentes);

            for (Perfil perfil : perfiles) {

                perfil.actualizarPuntaje();
                actualizarPerfilDB(session, perfil.getPuntaje(), perfil.getCategoria(), perfil.getId());
            }

            actualizarComunidadDB(session);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private void actualizarComunidadDB(Session session){
        String sql = "UPDATE comunidad c " +
                "SET c.puntaje = (SELECT AVG(p.puntaje) FROM perfil p WHERE p.id_perfil = c.id_perfil)";
        session.createSQLQuery(sql).executeUpdate();
    }
    private void actualizarPerfilDB(Session session,Double nuevoPuntaje, Confianza nuevaCategoria, Long id){

        Query query = session.createQuery(update_query);
        query.setParameter("tabla", "Perfil");
        query.setParameter("nuevoPuntaje", nuevoPuntaje);
        query.setParameter("nuevaCategoria", nuevaCategoria);
        query.setParameter("entityId", id);

        query.executeUpdate();
    }

    private List<Perfil> getPerfiles(Session session, List<Incidente> incidentes){

        List<Long> listaPerfiles = new ArrayList<>();

        for (Incidente incidente : incidentes) {
            listaPerfiles.add(incidente.getId_perfil_apertura());
            listaPerfiles.add(incidente.getId_perfil_cierre());
        }

        String hql = "SELECT id_perfil, confianza, nombre, apellido, monitoreable, puntaje FROM Perfil " +
                "WHERE id_perfil_apertura in :listaPerfiles" +
                "     OR id_perfil_cierre  in :listaPerfiles";

        Query query = session.createQuery(hql, Incidente.class);
        query.setParameter("listaPerfiles", listaPerfiles);



        return perfiles;
    }
    private List<Incidente> getIncidentes(Session session) {

        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime ultimoDomingo = ahora.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).withHour(13).withMinute(0).withSecond(0);

        String hql = "SELECT id_incidente, comunidad, apertura, cierre, id_perfil_apertura, id_perfil_cierre FROM Incidente WHERE apertura >= :ultimoDomingo";

        Query query = session.createQuery(hql, Incidente.class);
        query.setParameter("ultimoDomingo", ultimoDomingo);

        return query.list();
    }



}


