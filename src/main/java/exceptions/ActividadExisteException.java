package exceptions;

/**
 * Excepción que se lanza cuando se intenta crear una actividad que ya existe en el sistema.
 * Esta excepción es de tipo RuntimeException ya que representa un error de lógica de negocio
 * que no debería ocurrir en una ejecución normal del programa.
 */
public class ActividadExisteException extends RuntimeException {
    public ActividadExisteException(String message) {
        super(message);
    }
}
