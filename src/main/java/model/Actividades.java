package model;

import interfaces.Estado;

import java.util.Date;

/**
 * Representa una actividad con información detallada sobre su nombre,
 * descripción, fechas de inicio y fin, estado actual y comentarios adicionales.
 */
public class Actividades {
    private String nombre;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private Estado estado;
    private String comentario;

    /**
     * Constructor que inicializa una nueva actividad con todos sus atributos.
     * @param nombre El nombre de la actividad.
     * @param descripcion La descripción de la actividad.
     * @param fechaInicio La fecha de inicio de la actividad.
     * @param fechaFin La fecha de finalización de la actividad.
     * @param estado El estado actual de la actividad.
     * @param comentario Comentarios adicionales sobre la actividad.
     */
    public Actividades(String nombre, String descripcion, Date fechaInicio, Date fechaFin, Estado estado, String comentario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.comentario = comentario;
    }

    /**
     * Obtiene el nombre de la actividad.
     * @return El nombre de la actividad.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la actividad.
     * @param nombre El nuevo nombre de la actividad.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción de la actividad.
     * @return La descripción de la actividad.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción de la actividad.
     * @param descripcion La nueva descripción de la actividad.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la fecha de inicio de la actividad.
     * @return La fecha de inicio.
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Establece la fecha de inicio de la actividad.
     * @param fechaInicio La nueva fecha de inicio.
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Obtiene la fecha de finalización de la actividad.
     * @return La fecha de finalización.
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * Establece la fecha de finalización de la actividad.
     * @param fechaFin La nueva fecha de finalización.
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * Obtiene el estado actual de la actividad.
     * @return El estado de la actividad.
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Establece el estado actual de la actividad.
     * @param estado El nuevo estado de la actividad.
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Obtiene los comentarios adicionales de la actividad.
     * @return El comentario de la actividad.
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Establece comentarios adicionales para la actividad.
     * @param comentario El nuevo comentario de la actividad.
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * Retorna una representación en texto del objeto Actividades,
     * mostrando todos sus atributos.
     * @return Una cadena de texto que representa la actividad.
     */
    @Override
    public String toString() {
        return "Actividades{" +
                "nombre='" + nombre +
                "\n\tdescripcion='" + descripcion +
                "\n\tfechaInicio=" + fechaInicio +
                "\n\tfechaFin=" + fechaFin +
                "\n\testado=" + estado +
                "\n\tcomentario='" + comentario;
    }

}