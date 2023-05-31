
import Usuarios.Register;
import java.util.Scanner;

// App implementa el sistema de registro para esta entrega.
// corra este archivo en intellij para probarlo.

public class App {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print( "Ingresa Usuario: ");
        String user = sc.nextLine();
        System.out.print( "Ingresa contraseña: ");
        String pass = sc.nextLine();

        Register.registrar(user,pass);

        CargarDatos.leerArchivo();
    }
}