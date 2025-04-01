package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una lista de actividades y permite su serialización en XML mediante JAXB.
 */
@XmlRootElement(name = "Actividades")
@XmlAccessorType(XmlAccessType.FIELD)
public class ActividadLista {
    /**
     * Conjunto de actividades almacenados en la lista.
     */
    @XmlElement(name = "actividad")
    private List<Actividad> actividades = new ArrayList<>();

    /**
     * Constructor vacío requerido por JAXB para la deserialización.
     */
    public ActividadLista() {}

    /**
     * Obtiene la lista de actividades almacenadas.
     * @return Un conjunto de objetos de tipo {@link Actividad}
     */
    public List<Actividad> getActividades() {
        return new ArrayList<>(actividades);
    }

    /**
     * Establece la lista de actividades almacenadas.
     * @param actividades Conjunto de actividades que se asignará a la lista.
     */
    public void setActividades(List<Actividad> actividades) {
        this.actividades = new ArrayList<>(actividades);
    }

    /**
     * Agrega una actividad a la lista si no está presente.
     * @param actividad La actividad que se desea agregar.
     * @return true si la actividad fue agregada con éxito, false si ya estaba en la lista.
     */
    public boolean addActividad(Actividad actividad) {
        return this.actividades.add(actividad);
    }

    /**
     * Elimina una actividad de la lista.
     * @param actividad La actividad a eliminar.
     * @return true si la actividad fue eliminada, false si no existía.
     */
    public boolean removeActividad(Actividad actividad) {
        return this.actividades.remove(actividad);
    }

    /**
     * Verifica si una actividad existe en la lista.
     * @param actividad La actividad a buscar.
     * @return true si la actividad existe, false en caso contrario.
     */
    public boolean containsActividad(Actividad actividad) {
        return this.actividades.contains(actividad);
    }

    /**
     * Devuelve una representación en cadena de la lista de actividades.
     * @return Una cadena con la información de las actividades almacenadas.
     */
    public String toString() {
        return actividades.toString();
    }
}
