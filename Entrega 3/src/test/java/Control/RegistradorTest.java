package Control;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistradorTest {

    @Test
    @DisplayName("Registrar: Contraseña Débil")
    public void weakpass() {
        assertFalse(Registrador.registrar("Joaco","asdfghjkl"));
    }

    @Test
    @DisplayName("Registrar: Carácteres Inválidos")
    public void caracteresInvalidos() {
        assertFalse(Registrador.registrar("Joaco","Hello\nWorld!"));
    }

    @Test
    @DisplayName("Registrar: Espacios Consecutivos")
    public void espaciosConsecutivos() {
        assertFalse(Registrador.registrar("Joaco","z                               bc123"));
    }

}