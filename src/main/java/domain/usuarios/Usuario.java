package domain.usuarios;
import lombok.Getter;

@Getter
public class Usuario
{
    String user_id;
    String contrasena;

    public Usuario(String id, String pass)
    {
        user_id = id;
        contrasena = pass;
    }
}
