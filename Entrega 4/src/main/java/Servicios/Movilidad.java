package Servicios;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Movilidad extends Servicio{
    @Column
    private TipoMovilidad tipoMovilidad;

    public TipoMovilidad getTipoMovilidad() {
        return tipoMovilidad;
    }

    public void setTipoMovilidad(TipoMovilidad tipoMovilidad) {
        this.tipoMovilidad = tipoMovilidad;
    }

    public Movilidad() {
    }
}
