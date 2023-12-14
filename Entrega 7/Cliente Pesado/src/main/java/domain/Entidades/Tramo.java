package domain.Entidades;

import domain.Servicios.Monitoreable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Tramo {
    @Id
    @GeneratedValue
    private long id_tramo;

    @OneToOne
    @JoinColumn(name="ID_MONITOREABLE")
    private Monitoreable monitoreable;

    @ManyToOne
    @JoinColumn(name="ID_ESTABLECIMIENTO")
    private Establecimiento establecimiento;

    @Column
    private String descripcion;


}
