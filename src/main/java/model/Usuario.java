package model;

public abstract class Usuario {
    private String nombre;
    private String usuario;
    private String contraseña;
    private String email;

    //Constructores
    public Usuario(){

    }

    public Usuario(String nombre, String usuario, String contraseña, String email) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contraseña = contraseña;
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    //Métodos
    public boolean validarEmail(String email){


        return false;
    }
}
