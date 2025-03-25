package model;

public class Voluntario extends Usuario{
    private int puntos = 0;

    //Constructores
    public Voluntario(int puntos) {
        this.puntos = puntos;
    }

    public Voluntario(String nombre, String usuario, String contraseña, String email, int puntos) {
        super(nombre, usuario, contraseña, email);
        this.puntos = puntos;
    }

    //Getters y setters
    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}