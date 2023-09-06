import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CargarDatosTest {
    private static CargarDatos singleton;

    @BeforeAll
    static void setUpBase() {
        singleton = CargarDatos.getInstance();
        singleton.leerCSVs();
    }

    @Test
    @DisplayName("Lectura de CSV, seccion Entidades")
    public void testLecturaCSVEntidades() {
        String[][] matriz_esperada = {
                {"Banco", "Santander"},
                {"Hospital", "Austral"},
                {"Supermercado", "Carrefour"}
        };

        String[][] matriz_leida = singleton.getEntidadesData();

        assertNotNull(matriz_leida);
        assertArrayEquals(matriz_leida, matriz_esperada);
    }

    @Test
    @DisplayName("Lectura de CSV, seccion Organismos de Control")
    void testLecturaCSVOrganismos() {
        String[][] organismos_leidos = singleton.getOrganismosData();
        String[][] organismos_esperados = {
                {"Sindicatura General de la Nación (SIGEN)"},
                {"Unidades de Auditoría Interna (UAI)"},
                {"Auditoría General de la Nación (AGN)"}
        };

        assertNotNull(organismos_leidos);
        assertArrayEquals(organismos_leidos, organismos_esperados);
    }
}
