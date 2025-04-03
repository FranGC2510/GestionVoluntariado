package exceptions;

/**
 * Excepción que se lanza cuando se intenta utilizar una fecha no válida en el sistema.
 * Se utiliza en casos como:
 * - Fechas de inicio posteriores a fechas de fin
 * - Fechas en formato incorrecto
 * - Fechas fuera del rango permitido
 * - Fechas ilógicas (como 30 de febrero)
 */
public class FechaNoValidaException extends RuntimeException {
    public FechaNoValidaException(String message) {
        super(message);
    }
}
