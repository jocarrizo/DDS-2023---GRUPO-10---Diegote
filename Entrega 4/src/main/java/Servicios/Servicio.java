package Servicios;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class Servicio {

    @Id
    @GeneratedValue
    private long id_monitoreable;

    @Column (name="disponibilidad")
    private Boolean disponibilidad;

    @Column (name="tipo_servicio")
    private TipoServicio tipoServicio;

    @Column (name="descripcion")
    private String descripcion;

    public boolean estaHabilitado(){
        return true;
    }
}
