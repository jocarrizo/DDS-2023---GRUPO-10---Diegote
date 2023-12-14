package domain.Locaciones;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Municipio {

    @Id
    private long id;

    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "PROVINCIA_ID")
    private String provincia_id;
    @Column(name = "PROVINCIA")
    private String provincia_nombre;

    @Column(name="LATITUD")
    private double centroide_lat;
    @Column(name="LONGITUD")
    private double centroide_lon;

    @ManyToOne
    @JoinColumn(name = "ID_PROVINCIA")
    private Provincia provincia;

    @Override
    public String toString() {
        return "\n\nMunicipio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", provincia_id=" + provincia_id +
                ", provincia_nombre='" + provincia_nombre + '\'' +
                ", centroide_lat=" + centroide_lat +
                ", centroide_lon=" + centroide_lon +
                ", provincia=" + (provincia != null ? provincia.toString() : "null") +
                '}';
    }
}
