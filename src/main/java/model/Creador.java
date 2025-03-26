package model;

public class Creador extends Usuario{
    private String ONG;
    /*
    TIENES QUE INCLUIR LOS COMENTARIOS DE TODOS LOS METODOS QUE HAGAS, ADEMÁS AÑADIR EL TOSTRING
    PARA HACER COMPROBACIONES AUNQUE LUEGO NO TENGA USO REAL.
     */
    //Constructores
    public Creador(String ONG) {
        this.ONG = ONG;
    }

    public Creador(String nombre, String usuario, String password, String email, String ONG) {
        super(nombre, usuario, password, email);
        this.ONG = ONG;
    }

    //Getters y setters
    public String getONG() {
        return ONG;
    }

    public void setONG(String ONG) {
        this.ONG = ONG;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n\tRol-> " + getClass() +
                "\n\tONG-> " + this.ONG;
    }
}