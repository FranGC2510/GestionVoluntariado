package model;

public class Creador extends Usuario{
    private String ONG;

    // Constructor con un solo atributo: Inicializa un objeto Creador con la ONG especificada
    public Creador(String ONG) {
        this.ONG = ONG;
    }

    // Constructor con todos los atributos: Inicializa un Creador con todos los datos
    public Creador(String nombre, String usuario, String password, String email, String ONG) {
        super(nombre, usuario, password, email);
        this.ONG = ONG;
    }

    // Getter para el atributo ONG
    public String getONG() {
        return ONG;
    }

    // Setter para el atributo ONG
    public void setONG(String ONG) {
        this.ONG = ONG;
    }

    // Metodo toString: Retorna una representacion en texto del objeto Creador
    @Override
    public String toString() {
        return super.toString() +
                "\n\tRol -> " + getClass().getSimpleName() +
                "\n\tONG -> " + this.ONG;
    }
}