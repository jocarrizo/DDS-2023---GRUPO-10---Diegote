package presentation.dto;

import example.hibernate.utils.BDUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import java.util.List;
@Getter
@Setter
public class DatoPerfil {
    private long idPerfil;
    private String nombrePerfil;
    private List<DatoComunidades_x_Perfil> comunidades_x_perfil;


    public DatoPerfil(){}
    public DatoPerfil(long idPerfil, String nombrePerfil) {
        EntityManager em = BDUtils.getEntityManager();

        this.idPerfil = idPerfil;
        this.nombrePerfil = nombrePerfil;
        comunidades_x_perfil =  em.createQuery("SELECT NEW presentation.dto.DatoComunidades_x_Perfil(t.comunidad.id_comunidad,t.comunidad.nombre, " +
                                "t.esObservador,t.esAdmin) FROM comunidad_x_perfil t where t.perfil.id_perfil = ?1", DatoComunidades_x_Perfil.class)
                                .setParameter(1,idPerfil)
                                .getResultList();

        em.close();
    }
}
