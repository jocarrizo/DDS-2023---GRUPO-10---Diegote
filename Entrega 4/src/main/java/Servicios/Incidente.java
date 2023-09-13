package Servicios;
import Entidades.Entidad;
import Usuarios.Comunidades.Comunidad;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity
public class Incidente {
    @Id
    @GeneratedValue
    private long id_incidente;

    @OneToOne
    @JoinColumn(name="ID_MONITOREABLE")
    private Monitoreable monitoreable;

    @ManyToOne
    @JoinColumn(name="ID_ENTIDAD")
    private Entidad entidad;

    @ManyToOne
    @JoinColumn(name = "ID_COMUNIDAD")
    private Comunidad comunidad;

    @Column
    private String observaciones;
    @Column
    private boolean abierto;
    @Column
    private Date apertura;
    @Column
    private Date cierre;
}