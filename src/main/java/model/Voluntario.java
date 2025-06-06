package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase que representa a un usuario con rol de voluntario en el sistema.
 * Un voluntario puede acumular puntos por su participación en actividades.
 * Hereda de la clase {@link Usuario}.
 */
@XmlRootElement (name = "voluntario")
public class Voluntario extends Usuario{
    private int puntos;

    /**
     * Constructor vacío requerido por JAXB para la deserialización.
     */
    public Voluntario() {
    }

    /**
     * Constructor con parámetros para inicializar un voluntario con puntos inicializados en 0.
     * @param nombre   Nombre del voluntario.
     * @param usuario  Nombre de usuario del voluntario.
     * @param password Contraseña del voluntario.
     * @param email    Dirección de correo electrónico del voluntario.
     */
    public Voluntario(String nombre, String usuario, String password, String email) {
        super(nombre, usuario, password, email);
        this.puntos = 0;
    }

    /**
     * Obtiene la cantidad de puntos acumulados por el voluntario.
     * @return Cantidad de puntos del voluntario.
     */
    @XmlElement (name = "puntosTotales")
    public int getPuntos() {
        return puntos;
    }

    /**
     * Establece la cantidad de puntos acumulados por el voluntario.
     * @param puntos Nueva cantidad de puntos del voluntario.
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    /**
     * Suma una cantidad de puntos al total del voluntario.
     * @param cantidad Cantidad de puntos a sumar.
     */
    public void sumarPuntos(int cantidad) {
        this.puntos += cantidad;
    }

    /**
     * Representación en cadena del objeto voluntario.
     * @return Cadena con la información del voluntario y sus puntos acumulados.
     */
    @Override
    public String toString() {
        return super.toString() +
                "\n\tRol -> " + getClass().getSimpleName() +
                "\n\tPuntos -> " + this.puntos;
    }
}