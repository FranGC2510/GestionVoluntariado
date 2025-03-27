package model;
import javax.xml.bind.annotation.*;
import java.util.HashSet;

/**
 * Clase que representa una lista de usuarios y permite su serialización en XML mediante JAXB.
 * La lista puede contener tanto objetos de tipo {@link Creador} como de tipo {@link Voluntario}.
 */
@XmlRootElement(name = "Usuarios")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsuariosLista {

    /**
     * Conjunto de usuarios almacenados en la lista.
     * Se serializan correctamente en XML distinguiendo entre {@link Creador} y {@link Voluntario}.
     */
    @XmlElementRefs({
            @XmlElementRef(type = Creador.class),
            @XmlElementRef(type = Voluntario.class)
    })
    private HashSet<Usuario> usuarios = new HashSet<>();

    /**
     * Constructor vacío requerido por JAXB para la deserialización.
     */
    public UsuariosLista() {}

    /**
     * Obtiene la lista de usuarios almacenados.
     * @return Un conjunto de objetos de tipo {@link Usuario}, que pueden ser instancias de {@link Creador} o {@link Voluntario}.
     */
    public HashSet<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Establece la lista de usuarios almacenados.
     * @param usuarios Conjunto de usuarios que se asignará a la lista.
     */
    public void setUsuarios(HashSet<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * Agrega un usuario a la lista si no está presente.
     * @param usuario El usuario que se desea agregar.
     * @return true si el usuario fue agregado con éxito, false si ya estaba en la lista.
     */
    public boolean addUsuario(Usuario usuario) {
        return this.usuarios.add(usuario);
    }

    /**
     * Devuelve una representación en cadena de la lista de usuarios.
     * @return Una cadena con la información de los usuarios almacenados.
     */
    public String toString() {
        return usuarios.toString();
    }

}
