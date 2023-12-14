package domain.Servicios;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Entity
public class Banio extends Servicio{
    @Enumerated(EnumType.STRING)
    private Genero genero;

    public Banio() {
        this.setTipoServicio(TipoServicio.BANIO);
    }
}
