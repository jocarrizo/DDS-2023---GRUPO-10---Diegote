package Locaciones.georef;

import Locaciones.Departamento;

import java.util.List;
import java.util.Optional;

public class ListadoDepartamentos {
    public int cantidad;
    public int total;
    public int inicio;
    public Parametro parametros;
    public List<Departamento> departamentos;

    public Optional<Departamento> departamentoDeId(int id){
        return this.departamentos.stream()
                .filter(d -> d.id == id)
                .findFirst();
    }

    private class Parametro {
        public List<String> campos;
    }
}