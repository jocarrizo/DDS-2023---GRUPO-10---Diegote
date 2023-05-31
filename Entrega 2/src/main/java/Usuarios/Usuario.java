package Usuarios;
import Locaciones.Locacion;
import Servicios.Servicio;
import lombok.Getter;
import java.util.List;


@Getter
public class Usuario
{
    private String nombre;
    private String apellido;
    private String correo;
    private List<Servicio> servicios;
    private Locacion locacion;

    private String user_id;
    private String contrasena;

    public Usuario(String id, String pass)
    {
        user_id = id;
        contrasena = pass;
    }
}
