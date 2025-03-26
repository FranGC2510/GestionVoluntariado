package model;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;

@XmlRootElement(name = "Usuarios")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsuariosLista {

    @XmlElement(name = "usuario",type = Usuario.class)
    private HashSet<Usuario> personas = new HashSet<>();

    public UsuariosLista() {}

    public HashSet<Usuario> getPersonas() {
        return personas;
    }
    public void setPersonas(HashSet<Usuario> usuarios) {
        this.personas = usuarios;
    }

    public boolean addPersona(Usuario usuarios) {
        return personas.add(usuarios);
    }

    public String toString() {
        return personas.toString();
    }

}
