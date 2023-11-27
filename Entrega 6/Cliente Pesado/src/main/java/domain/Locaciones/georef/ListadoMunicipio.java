package domain.Locaciones.georef;

import domain.Locaciones.Municipio;
import domain.Locaciones.Provincia;
import example.hibernate.utils.BDUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import java.util.List;
@Getter
@Setter
public class ListadoMunicipio {
    public int cantidad;
    public int inicio;
    public List<Municipio> municipios;
    private static int total;
    public Parametro parametros;
    private class Parametro {
        private boolean aplanar;
        private String provincia;

        @Override
        public String toString() {
            return "Parametro{" +
                    "aplanar=" + aplanar +
                    ", provincia='" + provincia + '\'' +
                    '}';
        }
    }

    private static ListadoMunicipio instance = null;
    private ListadoMunicipio() {}
    public static ListadoMunicipio getInstance() {
        if (instance == null) {
            instance = new ListadoMunicipio();
        }
        return instance;
    }

    public void persistir(){
        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);

        try {
            for (Municipio municipio : municipios) em.persist(municipio);
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
        return "\nListadoMunicipio{" +
                "cantidad=" + cantidad +
                ", inicio=" + inicio +
                ", parametros=" + parametros +
                ", municipios=" + municipios +
                ", total=" + total +
                '}';
    }


}