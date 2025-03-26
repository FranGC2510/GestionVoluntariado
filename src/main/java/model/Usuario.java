package model;

public abstract class Usuario {
    private String nombre;
    private String usuario;
    private String password;
    private String email;

    /*
    TIENES QUE INCLUIR LOS COMENTARIOS DE TODOS LOS METODOS QUE HAGAS, ADEMÁS AÑADIR EL TOSTRING
    PARA HACER COMPROBACIONES AUNQUE LUEGO NO TENGA USO REAL. LOS TOSTRING YA LOS HE HECHO YO Y
    TAMBIEN TE HE MODIFICADO EL VALIDA EMAIL PORQUE SE VALIDARIA DE OTRA FORMA NO CON UN MÉTODO
    ESPECIFICO PARA ELLO EN ESTA CLASE.
     */
    //Constructores
    public Usuario(){

    }

    public Usuario(String nombre, String usuario, String password, String email) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
        this.email = email;
    }

    //Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
