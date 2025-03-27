package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "creador")
public class Creador extends Usuario{
    private String ONG;

    public Creador(){
    }

    public Creador(String nombre, String usuario, String password, String email, String ONG) {
        super(nombre, usuario, password, email);
        this.ONG = ONG;
    }

    @XmlElement (name = "ONG")
    public String getONG() {
        return ONG;
    }

    public void setONG(String ONG) {
        this.ONG = ONG;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n\tRol-> " + getClass() +
                "\n\tONG-> " + this.ONG;
    }
}