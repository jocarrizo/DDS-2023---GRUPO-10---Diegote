package domain.Locaciones.georef;

import domain.Locaciones.Provincia;
import example.hibernate.utils.BDUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;

@Getter
@Setter
public class ListadoProvincias{
    private int cantidad;
    private int inicio;
    private Parametro parametros;
    private List<Provincia> provincias;

    private static ListadoProvincias instance = null;
    private ListadoProvincias() {}
    public static ListadoProvincias getInstance() {
        if (instance == null) {
            instance = new ListadoProvincias();
        }
        return instance;
    }

    public Provincia provinciaDeId(int id) {
        for (Provincia provincia : this.provincias) {
            if (provincia.getId() == id) {
                return provincia;
            }
        }
        return null;
    }

    public void persistir(){
        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);

        try {
            for (Provincia provincia : provincias) em.persist(provincia);
        } catch (Exception e){
            System.out.println(e.getMessage());
            BDUtils.rollback(em);
            em.close();
        }

        BDUtils.commit(em);
        em.close();
    }

    private static class Parametro {
        public List<String> campos;
    }

    @Override
    public String toString() {
        return "ListadoProvincias{" +
                "cantidad=" + cantidad +
                ", inicio=" + inicio +
                ", parametros=" + parametros +
                ", provincias=" + provincias +
                "\n\n}";
    }
}