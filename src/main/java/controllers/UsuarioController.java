package controllers;

import dataAccess.XMLManager;
import model.Creador;
import model.Usuario;
import model.UsuariosLista;
import model.Voluntario;
import views.VistaConsola;

public class UsuarioController {
    private UsuariosLista usuarios;
    /**
     * Constructor del controlador de usuarios.
     * @param usuarios La lista de usuarios que se gestionará mediante este controlador.
     */
    public UsuarioController(UsuariosLista usuarios) {
        this.usuarios = usuarios;
    }
    /*
    /**
     * Determina el tipo de usuario en función de la clase a la que pertenece.
     * @param usuario El objeto {@link Usuario} cuyo tipo se desea determinar.
     * @return El tipo de usuario:
     *         1 - Si el usuario es de tipo {@link Creador},
     *         2 - Si el usuario es de tipo {@link Voluntario},
     *
    public int tipoUsuario(Usuario usuario) {
        int tipo = 0;
        if(usuario.getClass()== Creador.class){
            tipo = 1;
        } else if(usuario.getClass()== Voluntario.class){
            tipo = 2;
        }
        return tipo;
    }
    */

    /**
     * Añade un nuevo usuario a la lista de usuarios y guarda los datos en un archivo XML.
     * @return true si el usuario fue añadido correctamente y los datos se guardaron en el archivo,
     *         false en caso contrario.
     */
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
