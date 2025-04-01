package controllers;

import dataAccess.XMLManager;
import model.Usuario;
import model.UsuariosLista;
import utils.PasswordUtilidades;
import views.VistaConsola;
import views.VistaUsuario;

public class UsuarioController {
    private UsuariosLista usuarios;
    /**
     * Constructor del controlador de usuarios.
     */
    public UsuarioController() {
        this.usuarios = XMLManager.readXML(new UsuariosLista(),"usuarios.xml");
        if(usuarios == null){
            usuarios = new UsuariosLista();
        }
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
    public boolean addUsuario(Usuario usuario) {
        boolean resultado = false;
        if(usuario!=null && usuarios.addUsuario(usuario)){
            XMLManager.writeXML(usuarios,"usuarios.xml");
            resultado = true;
        }
        return resultado;
    }

    public boolean iniciarSesion(){
        boolean resultado = false;
        SesionUsuario sesion=SesionUsuario.getInstance();
        String usuario= VistaUsuario.pideUsuario();
        String password=VistaUsuario.pidePassword();

        for(Usuario u: usuarios.getUsuarios()){
            if(u.getUsuario()!=null && u.getPassword()!=null && u.getUsuario().equals(usuario) && PasswordUtilidades.checkPassword(password,u.getPassword())){
                sesion.iniciarSesion(u);
                resultado = true;
                break;
            }
        }
        return resultado;
    }
}
