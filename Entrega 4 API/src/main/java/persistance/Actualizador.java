package persistance;

import domain.Comunidad;
import domain.Perfil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Actualizador implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // Incluir la lógica para actualizar los campos Puntaje y Categoria en la base de datos utilizando Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            List<Perfil> perfiles = getPerfiles();

            Map<String, Perfil> mapaPerfiles = new HashMap<>();
            for (Perfil perfil : mapaPerfiles.values()) {
                perfil.actualizarPuntaje();
            }

            List<Comunidad> comunidades = getComunidades();

            Map<String, Comunidad> mapaComunidades = new HashMap<>();
            for (Comunidad comunidad : mapaComunidades.values()) {
                comunidad.actualizarPuntaje();
            }

            //CAMBIAR LOS CAMPOS DE LOS PUNTAJES EN LA BASE DE DATOS


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

    private List<Perfil> getPerfiles(){
        /* ESTARIA BUENO QUE TRAIGA SUS INCIDENTES Y LOS GUARDE EN UNA LISTA
        QUERY:
                SELECT p.id_perfil,
                       nombre,
                       apellido,
                       puntaje,
                       i.id_incidente
                FROM Perfil p
                WHERE
                    (SELECT id_perfil_apertura
                    FROM Incidente
                    WHERE (id_perfil_apertura = p.id_perfil OR id_perfil_cierre = p.id_perfil)
                    AND (apertura >= CURRENT_DATE() - INTERVAL 7 DAY OR cierre >= CURRENT_DATE() - INTERVAL 7 DAY)


        */
        return perfiles;
    }


    private List<Comunidad> getComunidades(){
        /*
        QUERY:
                SELECT p.id_perfil,
                       nombre,
                       apellido,
                       puntaje,
                       i.id_incidente
                FROM Perfil p
                WHERE
                    (SELECT id_perfil_apertura
                    FROM Incidente
                    WHERE (id_perfil_apertura = p.id_perfil OR id_perfil_cierre = p.id_perfil)
                    AND (apertura >= CURRENT_DATE() - INTERVAL 7 DAY OR cierre >= CURRENT_DATE() - INTERVAL 7 DAY)


        */
        return comunidades;
    }


    private List<Incidente> getIncidentes(){
        /*
        QUERY:

        */
        return incidentes;

    }


}

/* SELECT c.id_comunidad,
          cxp.id_perfil,
          c.incidentes,
          c.puntaje
   FROM Comunidad c
   JOIN Comunidad_X_Perfil cxp on c.id_comunidad = cxp.id_comunidad;




 */
