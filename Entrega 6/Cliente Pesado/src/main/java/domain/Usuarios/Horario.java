package domain.Usuarios;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Horario {
    @Id
    @GeneratedValue
    private long id_horario;

    @ManyToOne
    @JoinColumn(name = "ID_PERFIL")
    private Perfil perfil_asoc;

    @Column(name = "HORARIO")
    private Date horario;
}
