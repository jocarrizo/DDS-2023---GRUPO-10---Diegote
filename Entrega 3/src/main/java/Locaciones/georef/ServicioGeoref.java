package Locaciones.georef.georefService;

import Locaciones.ListadoProvincias;
import Locaciones.ListadoMunicipio;
import Locaciones.Provincia;
import Locaciones.georef.georefService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import config.Config;

import java.io.IOException;

public class ServicioGeoref {
    private static ServicioGeoref instancia = null;
    private static final int maximaCantidadRegistrosDefault = 200;
    private static final String urlApi = Config.URL_API;
    private final Retrofit retrofit;

    private ServicioGeoref() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ServicioGeoref instancia(){
        if(instancia== null){
            instancia = new ServicioGeoref();
        }
        return instancia;
    }

    public ListadoProvincias listadoProvincias() throws IOException {
        georefService georefService = this.retrofit.create(georefService.class);
        Call<ListadoProvincias> requestProvinciasArgentinas = georefService.provincias();
        Response<ListadoProvincias> responseProvinciasArgentinas = requestProvinciasArgentinas.execute();
        return responseProvinciasArgentinas.body();
    }

    public ListadoMunicipio listadoMunicipiosProvincia(Provincia provincia) throws IOException {
        georefService georefService = this.retrofit.create(georefService.class);
        Call<ListadoMunicipio> requestListadoDeMunicipios = georefService.municipios(provincia.id, "id, nombre", maximaCantidadRegistrosDefault);
        Response<ListadoMunicipio> responseListadoDeMunicipios = requestListadoDeMunicipios.execute();
        return responseListadoDeMunicipios.body();
    }
}
