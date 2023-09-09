package Locaciones.georef;

import Locaciones.Provincia;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;
@Getter
@Setter
public class ListadoProvincias{
    private int cantidad;
    private int inicio;
    private Parametro parametros;
    private List<Provincia> provincias;

    private static ListadoProvincias instance = null;
    private ListadoProvincias() {}
    public static ListadoProvincias getInstance() {
        if (instance == null) {
            instance = new ListadoProvincias();
        }
        return instance;
    }

    public Provincia provinciaDeId(int id) {
        for (Provincia provincia : this.provincias) {
            if (provincia.id == id) {
                return provincia;
            }
        }
        return null;
    }

    private static class Parametro {
        public List<String> campos;
    }

    @Override
    public String toString() {
        return "ListadoProvincias{" +
                "cantidad=" + cantidad +
                ", inicio=" + inicio +
                ", parametros=" + parametros +
                ", provincias=" + provincias +
                "\n\n}";
    }
}