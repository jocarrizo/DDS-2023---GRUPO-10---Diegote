package Locaciones;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Centroide {
    private float lat;
    private float lon;

    @Override
    public String toString() {
        return "Centroide{" +
                "lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}

