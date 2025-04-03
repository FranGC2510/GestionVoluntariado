package exceptions;

/**
 * Excepción que se lanza cuando una contraseña no cumple con los requisitos de seguridad.
 * Se utiliza en casos como:
 * - Contraseña demasiado corta
 * - Ausencia de mayúsculas/minúsculas
 * - Contraseñas que no coinciden en la confirmación
 */
public class PasswordInvalidaException extends RuntimeException {
    public PasswordInvalidaException(String message) {
        super(message);
    }
}
