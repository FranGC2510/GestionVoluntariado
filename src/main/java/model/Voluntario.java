package model;

public class Voluntario extends Usuario{
    private int puntos = 0;

    // Constructor con un solo atributo: Inicializa un objeto Voluntario con puntos especificados
    public Voluntario(int puntos) {
        this.puntos = puntos;
    }

    // Constructor con todos los atributos: Inicializa un Voluntario con todos los datos
    public Voluntario(String nombre, String usuario, String password, String email, int puntos) {
        super(nombre, usuario, password, email);
        this.puntos = puntos;
    }

    // Getter para el atributo puntos
    public int getPuntos() {
        return puntos;
    }

    // Setter para el atributo puntos
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    // Metodo toString: Retorna una representacion en texto del objeto Voluntario
    @Override
    public String toString() {
        return super.toString() +
                "\n\tRol -> " + getClass().getSimpleName() +
                "\n\tPuntos -> " + this.puntos;
    }
}