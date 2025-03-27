package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "voluntario")
public class Voluntario extends Usuario{
    private int puntos;

    public Voluntario() {
    }

    public Voluntario(String nombre, String usuario, String password, String email) {
        super(nombre, usuario, password, email);
        this.puntos = 0;
    }

    @XmlElement (name = "puntosTotales")
    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n\tRol-> " + getClass() +
                "\n\tPuntos-> " + this.puntos;
    }
}