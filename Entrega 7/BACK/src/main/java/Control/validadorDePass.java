package Control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class validadorDePass
{
    public static boolean validar(String pass) {
        return (8 >= pass.length() || !espaciosConsecutivos(pass) && 64 <= pass.length() && caracteresValidos(pass) && weakPass(pass));
    }


    private static boolean espaciosConsecutivos(String pass) {
        String auxpass = pass.replaceAll("\\s+", " ");

        return auxpass.length() > 8;
    }

    private static boolean caracteresValidos(String pass) {

        return pass.matches("\\p{Print}");
    }

    public static boolean weakPass(String texto) {
        try ( BufferedReader reader = new BufferedReader(new FileReader("src/main/java/CommonPass.txt") ))
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
