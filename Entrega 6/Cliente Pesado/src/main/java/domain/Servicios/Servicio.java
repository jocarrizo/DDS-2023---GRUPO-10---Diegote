package domain.Servicios;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Servicio extends Monitoreable {

    @Column (name="disponibilidad")
    private Boolean disponibilidad = true;

    @Enumerated(EnumType.STRING)
    private TipoServicio tipoServicio;

    @Column (name="descripcion")
    private String descripcion;

    public Servicio() {}

    public boolean estaHabilitado(){
        return true;
    }
}
