package controllers;

import model.Usuario;

public class SesionUsuario {
    private static SesionUsuario instance;
    private Usuario usuarioActual;

    private SesionUsuario() {}

    public static SesionUsuario getInstance() {
        if(instance == null) {
            instance = new SesionUsuario();
        }
        return instance;
    }

    public Usuario getUsuarioActual() {
        return this.usuarioActual;
    }

    public void iniciarSesion(Usuario usuario) {
        this.usuarioActual = usuario;
    }


}
