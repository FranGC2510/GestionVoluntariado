package interfaces;

/**
 * Enum que representa los posibles estados de una actividad en el sistema.
 * Los estados posibles son:
 * - PENDIENTE: La actividad está creada pero aún no ha comenzado
 * - EN_CURSO: La actividad está actualmente en ejecución
 * - FINALIZADA: La actividad ha sido completada
 */
public enum Estado {
    PENDIENTE,
    EN_CURSO,
    FINALIZADA
}