package InicioDeSesion.Inicio_De_Sesion.Service;
import org.mindrot.jbcrypt.BCrypt;

public class EncripatadorDeClaves {
    public static String encriptar(String clave) {
        return BCrypt.hashpw(clave, BCrypt.gensalt());
    }

    public static boolean comparar(String clave, String hashGuardado) {
        return BCrypt.checkpw(clave, hashGuardado);
    }

    
}
