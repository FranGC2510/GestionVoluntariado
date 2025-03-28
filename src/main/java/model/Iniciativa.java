package model;

import java.util.List;

/**
 * Representa una iniciativa con un identificador único, nombre, descripción,
 * un creador y una lista de voluntarios asociados.
 */
public class Iniciativa {
    private int id;
    private String nombre;
    private String descripcion;
    private Creador creador;
    private List<Voluntario> voluntarios;

    /**
     * Constructor que inicializa una nueva iniciativa con todos sus atributos.
     * @param id El identificador único de la iniciativa.
     * @param nombre El nombre de la iniciativa.
     * @param descripcion La descripción detallada de la iniciativa.
     * @param creador El creador responsable de la iniciativa.
     * @param voluntarios La lista de voluntarios que participan en la iniciativa.
     */
    public Iniciativa(int id, String nombre, String descripcion, Creador creador, List<Voluntario> voluntarios) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creador = creador;
        this.voluntarios = voluntarios;
    }

    /**
     * Obtiene el identificador único de la iniciativa.
     * @return El ID de la iniciativa.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único de la iniciativa.
     * @param id El nuevo ID de la iniciativa.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la iniciativa.
     * @return El nombre de la iniciativa.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la iniciativa.
     * @param nombre El nuevo nombre de la iniciativa.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción de la iniciativa.
     * @return La descripción de la iniciativa.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción de la iniciativa.
     * @param descripcion La nueva descripción de la iniciativa.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el creador de la iniciativa.
     * @return El creador de la iniciativa.
     */
    public Creador getCreador() {
        return creador;
    }

    /**
     * Establece el creador de la iniciativa.
     * @param creador El nuevo creador de la iniciativa.
     */
    public void setCreador(Creador creador) {
        this.creador = creador;
    }

    /**
     * Obtiene la lista de voluntarios que participan en la iniciativa.
     * @return La lista de voluntarios.
     */
    public List<Voluntario> getVoluntarios() {
        return voluntarios;
    }

    /**
     * Establece la lista de voluntarios de la iniciativa.
     * @param voluntarios La nueva lista de voluntarios.
     */
    public void setVoluntarios(List<Voluntario> voluntarios) {
        this.voluntarios = voluntarios;
    }

    /**
     * Retorna una representación en texto de la iniciativa,
     * mostrando sus atributos principales.
     * @return Una cadena de texto que representa la iniciativa.
     */
    @Override
    public String toString() {
        return "Iniciativa: " +
                "\n\tid=" + id +
                "\n\tnombre='" + nombre +
                "\n\tdescripcion='" + descripcion +
                "\n\tcreador=" + creador +
                "\n\tvoluntarios=" + voluntarios;
    }
}
