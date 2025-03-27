package model;
import javax.xml.bind.annotation.*;
import java.util.HashSet;

@XmlRootElement(name = "Usuarios")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsuariosLista {

    @XmlElementRefs({
            @XmlElementRef(type = Creador.class),
            @XmlElementRef(type = Voluntario.class)
    })
    private HashSet<Usuario> usuarios = new HashSet<>();

    public UsuariosLista() {}

    public HashSet<Usuario> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(HashSet<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public boolean addUsuario(Usuario usuarios) {
        return this.usuarios.add(usuarios);
    }

    public String toString() {
        return usuarios.toString();
    }

}
