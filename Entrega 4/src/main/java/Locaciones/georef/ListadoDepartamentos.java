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

    public Optional<Departamento> departamentoDeId(int id){
        return this.departamentos.stream()
                .filter(d -> d.id == id)
                .findFirst();
    }

    private class Parametro {
        public List<String> campos;
    }
}