package model;
import exceptions.EmailInvalidoException;
import exceptions.PasswordInvalidaException;
import utils.PasswordUtilidades;
import utils.UtilidadesGenerales;

import javax.xml.bind.annotation.*;

/**
 * Clase abstracta que representa un usuario dentro del sistema.
 * Puede ser extendida por {@link Creador} y {@link Voluntario}.
 * Se utiliza JAXB para la serialización en XML.
 */
@XmlRootElement (name="usuario")
@XmlSeeAlso({Creador.class, Voluntario.class})
public abstract class Usuario {

    private String nombre;
    private String usuario;
    private String password;
    private String email;

    /**
     * Constructor vacío requerido por JAXB para la deserialización.
     */
    public Usuario(){}

    /**
     * Constructor con parámetros para inicializar un usuario.
     * @param nombre   Nombre del usuario.
     * @param usuario  Nombre de usuario.
     * @param password Contraseña del usuario.
     * @param email    Dirección de correo electrónico del usuario.
     */
    public Usuario(String nombre, String usuario, String password, String email) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
        this.email = email;
    }

    /**
     * Obtiene el nombre del usuario.
     * @return Nombre del usuario.
     */
    @XmlElement (name = "nombre")
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     * @param nombre Nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre de usuario utilizado para autenticación.
     * @return Nombre de usuario.
     */
    @XmlElement (name = "nombreUsuario")
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el nombre de usuario.
     * @param usuario Nombre de usuario.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la contraseña del usuario.
     * @return Contraseña del usuario.
     */
    @XmlElement (name = "contraseña")
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * @param password Contraseña del usuario.
     */
    public void setPassword(String password) throws PasswordInvalidaException {
        if(!PasswordUtilidades.validaPassword(password)){
            throw new PasswordInvalidaException("Contraseña invalida, no cumple con el formato");
        }
        this.password = password;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     * @return Correo electrónico del usuario.
     */
    @XmlElement (name = "email")
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario después de validar que tiene un formato correcto.
     *
     * Este método verifica si el correo electrónico proporcionado cumple con un formato válido utilizando
     * la función {@link UtilidadesGenerales#validaEmail(String)}. Si el formato es incorrecto, se lanza una excepción {@link EmailInvalidoException}.
     *
     * @param email El correo electrónico que se desea establecer.
     * @throws EmailInvalidoException Si el formato del correo electrónico no es válido.
     */
    public void setEmail(String email) throws EmailInvalidoException {
        if(!UtilidadesGenerales.validaEmail(email)){
            throw new EmailInvalidoException("Formato incorrecto del email.");
        }
        this.email = email;
    }

    /**
     * Representación en cadena del usuario.
     * @return Cadena con la información del usuario.
     */
    @Override
    public String toString() {
        return "Usuario-> " + this.usuario+
                "\n\tNombre-> " + this.nombre +
                "\n\tContraseña-> " + this.password +
                "\n\tEmail-> " + this.email;
    }
}
