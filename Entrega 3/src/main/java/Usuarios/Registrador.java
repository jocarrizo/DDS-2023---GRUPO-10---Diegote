package Usuarios;
import java.util.Scanner;

public class Registrador
{
    static Scanner sc = new Scanner(System.in);

    public static void registrar(String user, String pass)
    {

        while( user.equals(pass) || !validadorDePass.validar(pass) )
        {
        System.out.println("Ingrese nueva contraseña: ");
        pass = sc.nextLine();
        }

          Usuario new_user = new Usuario(user,pass);
          System.out.println("Nuevo usuario \nid: "+new_user.getUsuario()+"\npass: "+ new_user.getContrasenia());

//          guardar usuario en Base.
    }
}
