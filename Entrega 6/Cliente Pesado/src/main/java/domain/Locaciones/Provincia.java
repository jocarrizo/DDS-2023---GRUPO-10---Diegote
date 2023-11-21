package domain.Locaciones;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Provincia {

    public int id;
    public String nombre;
    public Centroide centroide;

    @Override
    public String toString() {
        return "\nProvincia{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", centroide=" + centroide +
                '}';
    }
}
