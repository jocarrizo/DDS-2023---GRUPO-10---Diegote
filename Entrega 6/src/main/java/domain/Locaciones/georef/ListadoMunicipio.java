package domain.Locaciones.georef;

import domain.Locaciones.Municipio;
import domain.Locaciones.Provincia;
import example.hibernate.utils.BDUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import java.util.Arrays;
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
        //HAY PROVINCIAS QUE NO TIENEN MUNICIPIOS, SE CORTA EJECUCION
        if(municipios.isEmpty()) return;

        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);

        Provincia prov = em.createQuery("SELECT p FROM Provincia p where p.id = ?1",Provincia.class)
                .setParameter(1,Long.parseLong(municipios.get(0).getProvincia_id()))
                .getSingleResult();


        try {
            for (Municipio municipio : municipios) {
                municipio.setProvincia(prov);
                em.persist(municipio);
            }
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