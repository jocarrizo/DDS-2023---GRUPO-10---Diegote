package domain.Sistema_de_Registro;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class validadorDePass
{

    public static boolean validar(String pass)
    {
        if(8 > pass.length() || !espaciosConsecutivos(pass))
        {
            System.out.println("Contraseña muy corta. Debe contener al menos 8 caracteres.");
            return false;
        }

        if(64 < pass.length())
        {
            System.out.println("Contraseña muy larga. Debe contener menos de 64 caracteres.");
            return false;
        }

        if(!caracteresValidos(pass))
        {
            System.out.println("Posee caracteres no admitidos.");
            return false;
        }

        if(weakPass(pass))
        {
            System.out.println("La contrasenia ingresada es muy debil.");
            return false;
        }

    return true;
    }


    private static boolean espaciosConsecutivos(String pass) {
        String auxpass = pass.replaceAll("\\s+", " ");

        return auxpass.length() > 8;
    }

    private static boolean caracteresValidos(String pass) {
        return pass.matches("\\p{Print}");
    }

    public static boolean weakPass(String texto) {
        try ( BufferedReader reader = new BufferedReader(new FileReader("CommonPass.txt") ))
        {
            String linea;
            while ( (linea = reader.readLine()) != null )
            {
                if (linea.equals(texto))
                {
                    return true;
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
