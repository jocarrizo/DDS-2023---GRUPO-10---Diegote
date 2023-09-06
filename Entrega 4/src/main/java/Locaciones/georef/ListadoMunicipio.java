package Locaciones.georef;

import Locaciones.Municipio;

import java.util.List;

public class ListadoMunicipio {
    public int cantidad;
    public int total;
    public int inicio;
    public Parametro parametros;
    public List<Municipio> municipios;

    private class Parametro {
        public List<String> campos;
        public int max;
        public List<String> provincia;
    }
}