package model;

public class Creador extends Usuario{
    private String ONG;

    //Constructores
    public Creador(String ONG) {
        this.ONG = ONG;
    }

    public Creador(String nombre, String usuario, String contraseña, String email, String ONG) {
        super(nombre, usuario, contraseña, email);
        this.ONG = ONG;
    }

    //Getters y setters
    public String getONG() {
        return ONG;
    }

    public void setONG(String ONG) {
        this.ONG = ONG;
    }
}