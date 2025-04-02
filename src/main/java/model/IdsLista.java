package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "IDs")
@XmlAccessorType(XmlAccessType.FIELD)
public class IdsLista {
    /**
     * Conjunto de iniciativas almacenados en la lista.
     */
    @XmlElement(name = "ids")
    private List<String> ids = new ArrayList<>();

    /**
     * Constructor vacío requerido por JAXB para la deserialización.
     */
    public IdsLista() {}

    /**
     * Obtiene la lista de iniciativas almacenados.
     * @return Un conjunto de objetos de tipo {@link Iniciativa}
     */
    public List<String> getIds() {
        return ids;
    }

    /**
     * Agrega un ID a la lista si no está presente.
     * @param id El ID que se desea agregar.
     * @return true si el ID fue agregada con éxito, false si ya estaba en la lista.
     */
    public boolean addIds(String id) {
        return this.ids.add(id);
    }

    /**
     * Verifica si un identificador específico existe en la lista.
     *
     * @param id El identificador a buscar
     * @return true si el identificador existe en la lista, false en caso contrario
     * @throws IllegalArgumentException si el id es null
     */
    public boolean containsIds(String id) {
        return this.ids.contains(id);
    }
}
