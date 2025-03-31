package model;

import exceptions.FechaNoValidaException;
import interfaces.Estado;
import utils.GeneradorID;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

/**
 * Representa una actividad con información detallada sobre su nombre,
 * descripción, fechas de inicio y fin, estado actual y comentarios adicionales.
 */
public class Actividad {
    private String Id;
    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Estado estado;
    private String comentario;
    private HashSet<Voluntario> voluntarios;

    /**
     * Constructor que inicializa una nueva actividad con todos sus atributos.
     * @param nombre El nombre de la actividad.
     * @param descripcion La descripción de la actividad.
     * @param fechaInicio La fecha de inicio de la actividad.
     * @param fechaFin La fecha de finalización de la actividad.
     */
    public Actividad(String nombre, String descripcion, String fechaInicio, String fechaFin) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        setFechaInicio(fechaInicio);// Se valida en el setter
        setFechaFin(fechaFin); // Se valida en el setter
        this.estado = Estado.PENDIENTE;
        this.comentario = comentario;
        this.Id = GeneradorID.generarID();
        this.voluntarios = new HashSet<>();
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
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Establece la fecha de inicio de la actividad.
     * @param fechaInicio La nueva fecha de inicio.
     */
    public void setFechaInicio(String fechaInicio) {
        LocalDate fecha = LocalDate.parse(fechaInicio, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if (fecha.isBefore(LocalDate.now())) {
            throw new FechaNoValidaException("La fecha de inicio no puede ser anterior a la fecha actual.");
        }
        this.fechaInicio = fecha;
    }

    /**
     * Obtiene la fecha de finalización de la actividad.
     * @return La fecha de finalización.
     */
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    /**
     * Establece la fecha de finalización de la actividad, validando que la fecha de in no sea anterior
     * a la fecha de inicio.
     * @param fechaFin La nueva fecha de finalización.
     * @throws FechaNoValidaException
     */
    public void setFechaFin(String fechaFin) {
        LocalDate fecha = LocalDate.parse(fechaFin, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if(fecha.isBefore(fechaInicio)) {
            throw new FechaNoValidaException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        this.fechaFin = fecha;
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

    public String getId() {
        return Id;
    }

    /**
     * Obtiene la lista de voluntarios que participan en la actividad.
     * @return La lista de voluntarios.
     */
    public HashSet<Voluntario> getVoluntarios() {
        return voluntarios;
    }

    /**
     * Establece la lista de voluntarios de la actividad.
     * @param voluntarios La nueva lista de voluntarios.
     */
    public void setVoluntarios(HashSet<Voluntario> voluntarios) {
        this.voluntarios = voluntarios;
    }

    /**
     * Retorna una representación en texto del objeto Actividades,
     * mostrando todos sus atributos.
     * @return Una cadena de texto que representa la actividad.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String resultado="Actividad:" +
                "\n\tIdentificador= " + Id +
                "\n\tNombre= " + nombre +
                "\n\tdescripcion= " + descripcion +
                "\n\tfechaInicio= " + (fechaInicio!=null ? fechaInicio.format(formatter) : "N/A") +
                "\n\tfechaFin= " + (fechaFin!=null ? fechaFin.format(formatter) : "N/A") +
                "\n\testado= " + estado +
                "\n\tcomentario= " + comentario +
                "\n\tVoluntarios= ";
        if(voluntarios.isEmpty()){
            resultado+="No hay voluntarios";
        }else{
            for(Voluntario voluntario:voluntarios){
                resultado+="\n\t\t- " + voluntario.getNombre()+" ("+voluntario.getUsuario()+")";
            }
        }
        return resultado;
    }

}