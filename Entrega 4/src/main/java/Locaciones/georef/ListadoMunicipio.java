package Locaciones.georef;

import Locaciones.Municipio;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ListadoMunicipio {
    public int cantidad;
    public int inicio;
    public List<Municipio> municipios;
    private static int total;
    public Parametro parametros;
    private class Parametro {
        private boolean aplanar;
        private String provincia;

        @Override
        public String toString() {
            return "Parametro{" +
                    "aplanar=" + aplanar +
                    ", provincia='" + provincia + '\'' +
                    '}';
        }
    }

    private static ListadoMunicipio instance = null;
    private ListadoMunicipio() {}
    public static ListadoMunicipio getInstance() {
        if (instance == null) {
            instance = new ListadoMunicipio();
        }
        return instance;
    }

    @Override
    public String toString() {
        return "\nListadoMunicipio{" +
                "cantidad=" + cantidad +
                ", inicio=" + inicio +
                ", parametros=" + parametros +
                ", municipios=" + municipios +
                ", total=" + total +
                '}';
    }


}