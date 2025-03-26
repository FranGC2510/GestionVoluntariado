package model;

public class Voluntario extends Usuario{
    private int puntos = 0;
    /*
    TIENES QUE INCLUIR LOS COMENTARIOS DE TODOS LOS METODOS QUE HAGAS, ADEMÁS AÑADIR EL TOSTRING
    PARA HACER COMPROBACIONES AUNQUE LUEGO NO TENGA USO REAL.
     */

    //Constructores
    public Voluntario(int puntos) {
        this.puntos = puntos;
    }

    public Voluntario(String nombre, String usuario, String password, String email, int puntos) {
        super(nombre, usuario, password, email);
        this.puntos = puntos;
    }

    //Getters y setters
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