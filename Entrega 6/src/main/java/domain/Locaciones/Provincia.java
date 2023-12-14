package domain.Locaciones;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Provincia {
    @Id
    private long id;

    @Column(name="NOMBRE")
    private String nombre;


    @Column(name="LATITUD")
    private double centroide_lat;
    @Column(name="LONGITUD")
    private double centroide_lon;


    public Provincia() {}

    public Provincia(long id, String nombre, long centroide_lat, long centroide_lon) {
        this.id = id;
        this.nombre = nombre;
        this.centroide_lat = centroide_lat;
        this.centroide_lon = centroide_lon;
    }

    @Override
    public String toString() {
        return "Provincia{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", centroide_lat=" + centroide_lat +
                ", centroide_lon=" + centroide_lon +
                '}';
    }
}
