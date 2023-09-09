package Locaciones.georef;

import Servicios.Servicio;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ListadoProvinciasTest {
    ListadoProvincias lp;
    @BeforeAll
    void setUp() {
        ServicioGeoref sg = ServicioGeoref.instancia();
        try {
            lp = sg.listadoProvincias();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void pruebaAPI(){
        System.out.println(lp.toString());
        assertNotNull(lp);
    }

}