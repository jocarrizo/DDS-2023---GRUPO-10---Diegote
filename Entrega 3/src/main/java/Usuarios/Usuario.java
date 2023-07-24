package Usuarios;
import Locaciones.Locacion;
import Servicios.Servicio;
import lombok.Getter;
import java.util.List;


@Getter
public class Usuario
{
    private String id_usuario;
    private String contrasenia;
    private List<Perfil> perfiles;
    private int nro_telefono;
    private String correo;

    public Usuario(String id, String pass)
    {
        id_usuario = id;
        contrasenia = pass;
    }
}
