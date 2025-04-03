package exceptions;

/**
 * Excepción que se lanza cuando se intenta crear una iniciativa
 * que ya existe en el sistema.
 */
public class IniciativaExisteException extends RuntimeException {

    public IniciativaExisteException(String message) {
        super(message);
    }
}
