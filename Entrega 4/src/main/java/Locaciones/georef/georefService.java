package Locaciones.georef;

import Locaciones.Provincia;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface georefService {
    @GET("provincias")
    Call<ListadoProvincias> provincias();

    @GET("provincias")
    Call<ListadoProvincias> provincias(@Query("campos") String campos);

    @GET("municipios?aplanar=true")
    Call<ListadoMunicipio> municipios(@Query("provincia") String idProvincia);

    @GET("municipios?aplanar=true")
    Call<ListadoMunicipio> municipios(@Query("provincia") String idProvincia, @Query("campos") String campos);

    @GET("municipios?aplanar=true")
    Call<ListadoMunicipio> municipios(@Query("provincia") String idProvincia, @Query("campos") String campos, @Query("max") int max);

    @GET("departamentos?aplanar=true")
    Call<ListadoDepartamentos> departamentos(@Query("provincia") String idProvincia);

    @GET("departamentos?aplanar=true")
    Call<ListadoDepartamentos> departamentos(@Query("provincia") String idProvincia, @Query("campos") String campos);

    @GET("departamentos?aplanar=true")
    Call<ListadoDepartamentos> departamentos(@Query("provincia") String idProvincia, @Query("campos") String campos, @Query("max") int max);
}