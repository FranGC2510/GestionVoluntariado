package model;

import controllers.SesionUsuario;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Representa una iniciativa con un identificador único, nombre, descripción,
 * un creador y una lista de voluntarios asociados.
 * Se utiliza JAXB para la serialización en XML.
 */
@XmlRootElement(name="iniciativa")
@XmlAccessorType(XmlAccessType.FIELD)
public class Iniciativa {
    private static int contadorID=1;
    private int id;
    private String nombre;
    private String descripcion;
    private String creador;

    @XmlElementWrapper (name = "actividades")
    @XmlElement (name = "actividad")
    private List<Actividad> actividades;

    public Iniciativa() {}
    /**
     * Constructor que inicializa una nueva iniciativa con todos sus atributos.
     * @param nombre El nombre de la iniciativa.
     * @param descripcion La descripción detallada de la iniciativa.
     */
    public Iniciativa(String nombre, String descripcion) {
        this.id = contadorID++;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creador = SesionUsuario.getInstance().getUsuarioActual().getNombre();
        this.actividades = new ArrayList<>();
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
    public String getCreador() {
        return creador;
    }

    /**
     * Establece el creador de la iniciativa.
     * @param creador El nuevo creador de la iniciativa.
     */
    public void setCreador(String creador) {
        this.creador = creador;
    }

    /**
     * Obtiene la lista de actividades que hay en la iniciativa.
     * @return La lista de actividades.
     */
    public List<Actividad> getActividades() {
        return actividades;
    }

    /**
     * Establece la lista de actividades de la iniciativa .
     * @param actividades La nueva lista de actividades.
     */
    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    /**
     * Retorna una representación en texto de la iniciativa,
     * mostrando sus atributos principales.
     * @return Una cadena de texto que representa la iniciativa.
     */
    @Override
    public String toString() {
        String resultado="Iniciativa: " +
                "\n\tCodigo ID -> " + id +
                "\n\tNombre -> " + nombre +
                "\n\tDescripcion -> " + descripcion +
                "\n\tCreador -> " + creador +
                "\n\tActividades -> ";
        if(actividades.isEmpty()){
            resultado+="No hay actividades";
        }else{
            for(Actividad act:actividades){
                resultado+="\n\t\t- " + act.getNombre();
            }
        }
        return resultado;
    }
}
