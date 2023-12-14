package presentation.dto;

import domain.Rankings.Informe;
import domain.Rankings.PosicionRanking;
import domain.Rankings.Ranking;
import example.hibernate.utils.BDUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class DatoInforme {

    private List<DatoRanking> listadoRankings ;

    public DatoInforme() {
        this.listadoRankings = new ArrayList<>();
        EntityManager em = BDUtils.getEntityManager();

        Informe informe = em.createQuery("SELECT i FROM Informe i order by i.fecha_creacion DESC",Informe.class).getResultList().get(0);

        for (Ranking ranking : informe.getRankings()){

            listadoRankings.add(new DatoRanking(ranking.getTipo_ranking(),ranking
                    .getRanking()
                    .stream()
                    .sorted(Comparator.comparing(PosicionRanking::getPuntaje))
                    .collect(Collectors.toList())));

        }

        em.close();
    }
}
