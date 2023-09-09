package Locaciones;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Municipio {

    private String nombre;
    private String id;
    private String provincia_id;
    private String provincia_nombre;
    private float centroide_lat, centroide_lon;

    private Departamento departamento;
    private Provincia provincia;

    @Override
    public String toString() {
        return "\n\nMunicipio{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", provincia_id=" + provincia_id +
                ", provincia_nombre='" + provincia_nombre + '\'' +
                ", centroide_lat=" + centroide_lat +
                ", centroide_lon=" + centroide_lon +
                ", departamento=" + /* departamento.toString()+ */
                ", provincia=" + /* provincia.toString()+ */
                '}';
    }
}
