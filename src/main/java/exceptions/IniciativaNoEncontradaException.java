package exceptions;

/**
 * Excepción que se lanza cuando no se encuentra una iniciativa con el ID especificado.
 */
public class IniciativaNoEncontradaException extends RuntimeException {

    public IniciativaNoEncontradaException(String message) {
        super(message);
    }
}
