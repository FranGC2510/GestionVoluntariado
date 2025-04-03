package exceptions;

/**
 * Excepción que se lanza cuando se intenta acceder o modificar una actividad que no existe en el sistema.
 * Se utiliza cuando se busca una actividad por su ID u otros criterios y no se encuentra en la base de datos
 * o en la estructura de datos correspondiente.
 */
public class ActividadNoEncontradaException extends RuntimeException {
    public ActividadNoEncontradaException(String message) {
        super(message);
    }
}
