package controllers;

import dataAccess.XMLManager;
import model.Creador;
import model.Usuario;
import model.UsuariosLista;
import model.Voluntario;
import views.VistaConsola;

public class UsuarioController {
    private UsuariosLista usuarios;

    public UsuarioController(UsuariosLista usuarios) {
        this.usuarios = usuarios;
    }

    public int tipoUsuario(Usuario usuario) {
        int tipo = 0;
        if(usuario.getClass()== Creador.class){
            tipo = 1;
        } else if(usuario.getClass()== Voluntario.class){
            tipo = 2;
        }
        return tipo;
    }

    public boolean addUsuario() {
        boolean resultado = false;
        Usuario nuevoUsuario= VistaConsola.registro(VistaConsola.tipoRegistro());
        if(usuarios.addUsuario(nuevoUsuario)){
            XMLManager.writeXML(usuarios,"usuarios.xml");
            resultado = true;
        }
        return resultado;
    }
}
