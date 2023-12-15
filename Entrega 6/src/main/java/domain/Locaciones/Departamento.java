package domain.Locaciones;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Departamento {

    @Id
    @GeneratedValue
    private long id_departamento;

    private String nombre;
    private String nombre_completo;
    private String provincia_id;
    private String provincia_nombre;
    @Column(name="LATITUD")
    private double centroide_lat;
    @Column(name="LONGITUD")
    private double centroide_lon;
    private String categoria;

    @ManyToOne
    @JoinColumn(name = "id")
    private Provincia provincia;


    public Departamento() {}

    public Departamento(long id_departamento, String nombre, String nombre_completo, String provincia_id, String provincia_nombre, long centroide_lat, long centroide_lon, String categoria, Provincia provincia) {
        this.id_departamento = id_departamento;
        this.nombre = nombre;
        this.nombre_completo = nombre_completo;
        this.provincia_id = provincia_id;
        this.provincia_nombre = provincia_nombre;
        this.centroide_lat = centroide_lat;
        this.centroide_lon = centroide_lon;
        this.categoria = categoria;
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "\nDepartamento{" +
                "id='" + id_departamento + '\'' +
                ", nombre='" + nombre + '\'' +
                ", nombre_completo='" + nombre_completo + '\'' +
                ", provincia_id='" + provincia_id + '\'' +
                ", provincia_nombre='" + provincia_nombre + '\'' +
                ", centroide_lat=" + centroide_lat +
                ", centroide_lon=" + centroide_lon +
                ", categoria='" + categoria + '\'' +
                ", provincia=" + (provincia != null ? provincia.toString() : "null") +
                '}';
    }
}
