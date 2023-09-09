package Control;
import Control.validadorDePass;
import Usuarios.Usuario;

import java.util.Scanner;

public class Registrador
{
    public static boolean registrar(String user, String pass)
    {
        if (user.equals(pass) || !validadorDePass.validar(pass)) return false;

        Usuario new_user = new Usuario();
        return true;
    }
}
