package model;

import java.util.HashSet;

/**
 * Representa una iniciativa con un identificador único, nombre, descripción,
 * un creador y una lista de voluntarios asociados.
 */
public class Iniciativa {
    private static int contadorID=1;
    private int id;
    private String nombre;
    private String descripcion;
    private Creador creador;
    private HashSet<Voluntario> voluntarios;

    /**
     * Constructor que inicializa una nueva iniciativa con todos sus atributos.
     * @param nombre El nombre de la iniciativa.
     * @param descripcion La descripción detallada de la iniciativa.
     * @param creador El creador responsable de la iniciativa.
     * @param voluntarios La lista de voluntarios que participan en la iniciativa.
     */
    public Iniciativa(String nombre, String descripcion, Creador creador, HashSet<Voluntario> voluntarios) {
        this.id = contadorID++;
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
    public HashSet<Voluntario> getVoluntarios() {
        return voluntarios;
    }

    /**
     * Establece la lista de voluntarios de la iniciativa.
     * @param voluntarios La nueva lista de voluntarios.
     */
    public void setVoluntarios(HashSet<Voluntario> voluntarios) {
        this.voluntarios = voluntarios;
    }

    /**
     * Retorna una representación en texto de la iniciativa,
     * mostrando sus atributos principales.
     * @return Una cadena de texto que representa la iniciativa.
     */
    @Override
    public String toString() {
        String resultado="Iniciativa: " +
                "\n\tCodigo ID= " + id +
                "\n\tNombre= " + nombre +
                "\n\tDescripcion= " + descripcion +
                "\n\tCreador= " + creador +
                "\n\tVoluntarios= ";
        if(voluntarios.isEmpty()){
            resultado+="No hay voluntarios";
        }else{
            for(Voluntario voluntario:voluntarios){
                resultado+="\n\t\t- " + voluntario.getNombre();
            }
        }
        return resultado;
    }
}
