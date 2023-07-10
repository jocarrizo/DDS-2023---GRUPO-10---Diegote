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
    private String usuario;
    private String contrasenia;
    private List<Servicio> servicios;
    private Locacion locacion;


    public Usuario(String id, String pass)
    {
        usuario = id;
        contrasenia = pass;
    }
}
