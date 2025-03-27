package exceptions;

/**
 * Excepción que se lanza cuando se intenta crear una iniciativa
 * que ya existe en el sistema.
 */
public class IniciativaAlreadyExistsException extends RuntimeException {

    /**
     * Constructor de la excepción que recibe un mensaje descriptivo del error.
     * @param message El mensaje que describe el error.
     */
    public IniciativaAlreadyExistsException(String message) {
        super(message);
    }
}
