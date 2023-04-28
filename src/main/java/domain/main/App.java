package domain.main;
import domain.Sistema_de_Registro.Register;
import java.util.Scanner;

public class App 
{
    static Scanner sc = new Scanner(System.in);

    public static void main( String[] args ) {
        System.out.print( "Ingresa Usuario: ");
        String user = sc.nextLine();
        System.out.print( "Ingresa contraseña: ");
        String pass = sc.nextLine();

        Register.registrar(user,pass);
    }
}
