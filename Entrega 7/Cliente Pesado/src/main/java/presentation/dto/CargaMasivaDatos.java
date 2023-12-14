package presentation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Setter @Getter
public class CargaMasivaDatos {

    private String tipo;
    private String data;
    public CargaMasivaDatos(){}

    public CargaMasivaDatos(String tipo, String data) {
        this.tipo = tipo;
        this.data = data;
    }

    public List<String[]> getDataAsList() {
        String[] rows = data.split("\r\n");
        List<String[]> resultList = new ArrayList<>();

        for (String row : rows) {
            String[] values = row.split(","); // Cambia la coma por el delimitador que uses en tu CSV
            resultList.add(values);
        }

        return resultList;
    }
}
