package domain.Servicios;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Banio extends Servicio{
    @Column
    private Genero genero;

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Banio() {
    }
}
