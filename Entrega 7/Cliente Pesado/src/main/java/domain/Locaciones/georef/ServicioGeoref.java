package domain.Locaciones.georef;

import domain.Locaciones.Provincia;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ServicioGeoref {
    private static ServicioGeoref instancia = null;
    private static int maximaCantidadRegistrosDefault = 200;
    private static final String urlApi = "https://apis.datos.gob.ar/georef/api/";
    private final Retrofit retrofit;

    private ServicioGeoref() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    //Singleton
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
        Call<ListadoMunicipio> requestListadoDeMunicipios = georefService.municipios(provincia.getNombre(),"id,nombre,provincia,centroide", maximaCantidadRegistrosDefault);
        Response<ListadoMunicipio> responseListadoDeMunicipios = requestListadoDeMunicipios.execute();
        return responseListadoDeMunicipios.body();
    }

    public ListadoDepartamentos listadoDepartamentosProvincia(Provincia provincia) throws IOException {
        georefService georefService = this.retrofit.create(georefService.class);
        Call<ListadoDepartamentos> requestListadoDeDepartamentos = georefService.departamentos(provincia.getNombre(), "id,nombre,nombre_completo,provincia,centroide,categoria", maximaCantidadRegistrosDefault);
        Response<ListadoDepartamentos> responseListadoDeDepartamentos = requestListadoDeDepartamentos.execute();
        return responseListadoDeDepartamentos.body();
    }
}
