package domain.Rankings;

import domain.Entidades.Entidad;
import domain.Entidades.EntidadPrestadora;
import domain.Rankings.GeneradorRankings.GeneradorRankingSemanal;
import example.hibernate.utils.BDUtils;
import lombok.Getter;
import lombok.Setter;
import presentation.dto.MisDatos;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RepositorioEntidades {
    private List<Entidad> repo = new ArrayList<>();

    private GeneradorRankingSemanal generadorRanking;

    private static RepositorioEntidades instance;

    private RepositorioEntidades() {

        EntityManager em = BDUtils.getEntityManager();

        this.repo = em.createQuery("SELECT t FROM Entidad t ", Entidad.class).getResultList();

        em.close();
    }

    public static RepositorioEntidades getInstance() {
        if (instance == null) {
            instance = new RepositorioEntidades();
        }
        return instance;
    }

    public void generarRanking() {
        this.generadorRanking.generarRanking(this.repo);
    }
}
