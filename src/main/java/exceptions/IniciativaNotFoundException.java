package exceptions;

/**
 * Excepción que se lanza cuando no se encuentra una iniciativa con el ID especificado.
 */
public class IniciativaNotFoundException extends RuntimeException {

    /**
     * Constructor de la excepción que recibe un mensaje descriptivo del error.
     * @param message El mensaje que describe el error ocurrido.
     */
    public IniciativaNotFoundException(String message) {
        super(message);
    }
}
