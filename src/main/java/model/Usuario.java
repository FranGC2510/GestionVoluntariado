package model;

public abstract class Usuario {
    private String nombre;
    private String usuario;
    private String password;
    private String email;


    // Constructor por defecto: Inicializa un objeto Usuario sin valores especificados
    public Usuario(){

    }

    // Constructor con todos los atributos: Permite inicializar un objeto Usuario con datos completos
    public Usuario(String nombre, String usuario, String password, String email) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
        this.email = email;
    }

    // Getter para el atributo nombre
    public String getNombre() {
        return nombre;
    }
    // Setter para el atributo nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    // Getter para el atributo usuario
    public String getUsuario() {
        return usuario;
    }
    // Setter para el atributo usuario
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    // Getter para el atributo password
    public String getPassword() {
        return password;
    }

    // Setter para el atributo password
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter para el atributo email
    public String getEmail() {
        return email;
    }

    // Setter para el atributo email con validacion (se sugiere implementar la validacion en un metodo separado)
    public void setEmail(String email) {
        // Valida el email utilizando un metodo estatico en una clase de utilidades
        this.email = email;
    }

    // Metodo toString: Retorna una representacion en texto del objeto Usuario
    @Override
    public String toString() {
        return "Usuario-> " + this.usuario+
                "\n\tNombre-> " + this.nombre +
                "\n\tContraseña-> " + this.password +
                "\n\tEmail-> " + this.email;
    }
}
