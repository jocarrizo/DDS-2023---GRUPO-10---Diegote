package Locaciones;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Departamento {

    public Provincia provincia;
    public String id;
    public String nombre;
    public String nombre_completo;
    public String provincia_id, provincia_nombre;
    public float centroide_lat, centroide_lon;
    public String categoria;

    @Override
    public String toString() {
        return "\nDepartamento{" +
                ", id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", nombre_completo='" + nombre_completo + '\'' +
                ", provincia_id='" + provincia_id + '\'' +
                ", provincia_nombre='" + provincia_nombre + '\'' +
                ", centroide_lat=" + centroide_lat +
                ", centroide_lon=" + centroide_lon +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
