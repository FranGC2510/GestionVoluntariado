package interfaces;

import java.util.List;

/**
 * Interfaz genérica que define las operaciones básicas de gestión CRUD para
 * cualquier tipo de entidad en el sistema.
 *
 * @param <T> Tipo de entidad que se gestionará (Actividad, Iniciativa, etc.)
 */
public interface Gestionable<T> {
    /**
     * Crea una nueva entidad en el sistema.
     *
     * @param t La entidad a crear
     * @return true si la creación fue exitosa, false en caso contrario
     * @throws RuntimeException si la entidad ya existe o hay errores de validación
     */
    boolean crear(T t);
    /**
     * Busca una entidad por su identificador único.
     *
     * @param id Identificador único de la entidad
     * @return La entidad encontrada o null si no existe
     */
    T buscarPorId(String id);
    /**
     * Elimina una entidad del sistema.
     *
     * @param t La entidad a eliminar
     * @return true si la eliminación fue exitosa, false si la entidad no existe
     */
    boolean eliminar(T t);
    /**
     * Obtiene una lista de todas las entidades disponibles.
     *
     * @return Lista de entidades, vacía si no hay ninguna
     */
    List<T> listar();
}