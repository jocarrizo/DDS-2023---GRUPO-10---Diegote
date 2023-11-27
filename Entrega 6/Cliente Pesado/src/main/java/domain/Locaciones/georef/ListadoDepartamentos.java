package domain.Locaciones.georef;

import domain.Locaciones.Departamento;
import domain.Locaciones.Provincia;
import example.hibernate.utils.BDUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import java.util.List;



@Getter
@Setter
public class ListadoDepartamentos {
    private int cantidad;
    private int total;
    private int inicio;
    private Parametro parametros;
    private List<Departamento> departamentos;

    private static ListadoDepartamentos instance = null;
    private ListadoDepartamentos() {}
    public static ListadoDepartamentos getInstance() {
        if (instance == null) {
            instance = new ListadoDepartamentos();
        }
        return instance;
    }


    public void persistir(){
        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);

        try {
            for (Departamento departamento : departamentos) em.persist(departamento);
        } catch (Exception e){
            System.out.println(e.getMessage());
            BDUtils.rollback(em);
            em.close();
        }

        BDUtils.commit(em);
        em.close();
    }

    @Override
    public String toString() {
        return "ListadoDepartamentos{" +
                "cantidad=" + cantidad +
                ", total=" + total +
                ", inicio=" + inicio +
                ", parametros=" + parametros.toString() +
                ", departamentos=" + departamentos +
                '}';
    }

    private static class Parametro {
        public List<String> campos;

        @Override
        public String toString() {
            return "Parametro{" +
                    "campos=" + campos +
                    '}';
        }
    }
}