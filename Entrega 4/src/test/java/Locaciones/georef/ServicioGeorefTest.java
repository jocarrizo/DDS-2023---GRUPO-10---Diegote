package Locaciones.georef;

import Locaciones.Provincia;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ServicioGeorefTest {
    ListadoProvincias lp;
    ServicioGeoref sg;

    @BeforeAll
    void setUp() {
        sg = ServicioGeoref.instancia();
        try {
            lp = sg.listadoProvincias();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void provincias(){
        System.out.println(lp.toString());
        assertNotNull(lp);
    }

    @Test
    void municipios(){
        ListadoMunicipio lm;
        Provincia p = lp.provinciaDeId(90);
        try {
            lm = sg.listadoMunicipiosProvincia(p);
            System.out.println(lm.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(lm);
    }
}