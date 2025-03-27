package model;
import javax.xml.bind.annotation.*;

@XmlRootElement (name="usuario")
@XmlSeeAlso({Creador.class, Voluntario.class})
public abstract class Usuario {
    private String nombre;
    private String usuario;
    private String password;
    private String email;



    public Usuario(){}

    public Usuario(String nombre, String usuario, String password, String email) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
        this.email = email;
    }

    @XmlElement (name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement (name = "nombreUsuario")
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @XmlElement (name = "contraseña")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlElement (name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        //Aqui se valida el email con un metodo estatico en utilidades donde iria la expresion regular.
        this.email = email;
    }

    @Override
    public String toString() {
        return "Usuario-> " + this.usuario+
                "\n\tNombre-> " + this.nombre +
                "\n\tContraseña-> " + this.password +
                "\n\tEmail-> " + this.email;
    }
}
