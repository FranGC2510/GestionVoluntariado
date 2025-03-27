package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase que representa a un usuario con rol de creador en el sistema.
 * Un creador está asociado a una organización no gubernamental (ONG).
 * Hereda de la clase {@link Usuario}.
 */
@XmlRootElement (name = "creador")
public class Creador extends Usuario{
    private String ONG;

    /**
     * Constructor vacío requerido por JAXB para la deserialización.
     */
    public Creador(){}

    /**
     * Constructor con parámetros para inicializar un creador con su ONG asociada.
     * @param nombre   Nombre del creador.
     * @param usuario  Nombre de usuario del creador.
     * @param password Contraseña del creador.
     * @param email    Dirección de correo electrónico del creador.
     * @param ONG      Nombre de la organización no gubernamental a la que pertenece.
     */
    public Creador(String nombre, String usuario, String password, String email, String ONG) {
        super(nombre, usuario, password, email);
        this.ONG = ONG;
    }

    /**
     * Obtiene el nombre de la ONG a la que pertenece el creador.
     * @return Nombre de la ONG.
     */
    @XmlElement (name = "ONG")
    public String getONG() {
        return ONG;
    }

    /**
     * Establece el nombre de la ONG a la que pertenece el creador.
     * @param ONG Nombre de la ONG.
     */
    public void setONG(String ONG) {
        this.ONG = ONG;
    }

    /**
     * Representación en cadena del objeto creador.
     * @return Cadena con la información del creador y su ONG.
     */
    @Override
    public String toString() {
        return super.toString() +
                "\n\tRol-> " + getClass() +
                "\n\tONG-> " + this.ONG;
    }
}