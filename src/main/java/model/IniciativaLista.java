package model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Clase que representa una lista de iniciativas y permite su serialización en XML mediante JAXB.
 */
@XmlRootElement(name = "Iniciativas")
@XmlAccessorType(XmlAccessType.FIELD)
public class IniciativaLista {
    /**
     * Conjunto de iniciativas almacenados en la lista.
     */
    @XmlElement (name = "iniciativa")
    private List<Iniciativa> iniciativas = new ArrayList<>();

    /**
     * Constructor vacío requerido por JAXB para la deserialización.
     */
    public IniciativaLista() {}

    /**
     * Obtiene la lista de iniciativas almacenados.
     * @return Un conjunto de objetos de tipo {@link Iniciativa}
     */
    public List<Iniciativa> getIniciativas() {
        return iniciativas;
    }

    /**
     * Establece la lista de iniciativas almacenados.
     * @param iniciativas Conjunto de iniciativas que se asignará a la lista.
     */
    public void setIniciativas(List<Iniciativa> iniciativas) {
        this.iniciativas = iniciativas;
    }

    /**
     * Agrega una iniciativa a la lista si no está presente.
     * @param iniciativa El iniciativa que se desea agregar.
     * @return true si la iniciativa fue agregada con éxito, false si ya estaba en la lista.
     */
    public boolean addIniciativa(Iniciativa iniciativa) {
        return this.iniciativas.add(iniciativa);
    }

    /**
     * Elimina una iniciativa de la lista.
     * @param iniciativa La iniciativa a eliminar.
     * @return true si la iniciativa fue eliminada, false si no existía.
     */
    public boolean removeIniciativa(Iniciativa iniciativa) {
        return this.iniciativas.remove(iniciativa);
    }

    /**
     * Verifica si una iniciativa existe en la lista.
     * @param iniciativa La iniciativa a buscar.
     * @return true si la iniciativa existe, false en caso contrario.
     */
    public boolean containsIniciativa(Iniciativa iniciativa) {
        return this.iniciativas.contains(iniciativa);
    }

    /**
     * Devuelve una representación en cadena de la lista de iniciativas.
     * @return Una cadena con la información de las iniciativas almacenadas.
     */
    @Override
    public String toString() {
        return iniciativas.toString();
    }
}
