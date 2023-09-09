package Servicios;
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

    @Transient
    private Monitoreable servicio;

    private String observaciones;
    private boolean abierto;
    private Date apertura;
    private Date cierre;
}