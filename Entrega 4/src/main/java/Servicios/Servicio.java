package Servicios;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class Servicio extends Monitoreable {

    @Column (name="disponibilidad")
    private Boolean disponibilidad;

    @Transient
    private TipoServicio tipoServicio;

    @Column (name="descripcion")
    private String descripcion;

    public Servicio() {}

    public boolean estaHabilitado(){
        return true;
    }
}
