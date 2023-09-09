package Locaciones.georef;

import Locaciones.Departamento;

import java.util.List;
import java.util.Optional;

public class ListadoDepartamentos {
    private int cantidad;
    private int total;
    private int inicio;
    private Parametro parametros;
    private List<Departamento> departamentos;

    private static ListadoDepartamentos instance = null;
    private ListadoDepartamentos() {}
    public static ListadoDepartamentos getInstance() {
        if (instance == null) {
            instance = new ListadoDepartamentos();
        }
        return instance;
    }


    @Override
    public String toString() {
        return "ListadoDepartamentos{" +
                "cantidad=" + cantidad +
                ", total=" + total +
                ", inicio=" + inicio +
                ", parametros=" + parametros.toString() +
                ", departamentos=" + departamentos +
                '}';
    }

    private static class Parametro {
        public List<String> campos;

        @Override
        public String toString() {
            return "Parametro{" +
                    "campos=" + campos +
                    '}';
        }
    }
}