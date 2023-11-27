package domain.Locaciones.georef;

import domain.Locaciones.Departamento;
import domain.Locaciones.Municipio;
import domain.Locaciones.Provincia;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class ServicioGeorefTest {

    @Test
    void instancia() throws IOException {
        ServicioGeoref sg = ServicioGeoref.instancia();
        // CARGA
        ListadoProvincias provincias = sg.listadoProvincias();

        List<ListadoDepartamentos> departamentos_x_provincia = new ArrayList<>();
        for(Provincia provincia : provincias.getProvincias()) {
            ListadoDepartamentos ld = sg.listadoDepartamentosProvincia(provincia);

            for(Departamento departamento : ld.getDepartamentos()){
                departamento.setProvincia(provincia);
            }

            departamentos_x_provincia.add(ld);
        }

        List<ListadoMunicipio> municipios_x_provincia = new ArrayList<>();
        for(Provincia provincia : provincias.getProvincias()) {
            ListadoMunicipio lm = sg.listadoMunicipiosProvincia(provincia);

            for(Municipio municipio : lm.getMunicipios()){
                municipio.setProvincia(provincia);
            }

            municipios_x_provincia.add(lm);
        }

        // PERSISTENCIA

        for(Provincia provincia : provincias.getProvincias()) {
            ListadoDepartamentos ld = sg.listadoDepartamentosProvincia(provincia);
            ld.persistir();
        }

        for(Provincia provincia : provincias.getProvincias()) {
            ListadoMunicipio lm = sg.listadoMunicipiosProvincia(provincia);
            lm.persistir();
        }

        provincias.persistir();
    }
}