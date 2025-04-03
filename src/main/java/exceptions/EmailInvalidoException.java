package exceptions;

/**
 * Excepción que se lanza cuando se intenta utilizar una dirección de correo electrónico inválida.
 * Se utiliza durante la validación de emails en procesos como:
 * - Registro de usuarios
 */
public class EmailInvalidoException extends RuntimeException {
    public EmailInvalidoException(String message) {
        super(message);
    }
}
