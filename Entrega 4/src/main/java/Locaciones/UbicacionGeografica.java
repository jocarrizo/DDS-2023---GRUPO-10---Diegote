package Locaciones;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UbicacionGeografica {

    private Municipio municipio;
    private String barrio;
    private Direccion direccion;
}
