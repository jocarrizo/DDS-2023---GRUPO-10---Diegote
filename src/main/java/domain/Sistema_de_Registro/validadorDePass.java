package domain.Sistema_de_Registro;


public class validadorDePass{

    public static boolean validar(String pass){

        if(8 > pass.length() || !espaciosConsecutivos(pass)) {
            System.out.println("Contraseña muy corta. Debe contener al menos 8 caracteres.");
            return false;
        }

        if(64 < pass.length()){
            System.out.println("Contraseña muy larga. Debe contener menos de 64 caracteres.");
            return false;
        }

        if(!caracteresValidos(pass)){
            //corrobora que la pass este dentro del lenguaje ASCII (imprimibles)
            System.out.println("Posee caracteres no admitidos.");
            return false;
        }

        // Contraseña no esta en la lista de Top 10k

        //


    return true;
    }

    private static boolean espaciosConsecutivos(String pass) {
        String auxpass = pass.replaceAll("\\s+", " ");

        return auxpass.length() > 8;
    }

    private static boolean caracteresValidos(String pass){
        return pass.matches("\\p{Print}");
    }
}
