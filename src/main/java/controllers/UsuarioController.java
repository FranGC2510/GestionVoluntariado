package controllers;

import dataAccess.XMLManager;
import model.Actividad;
import model.Usuario;
import model.UsuariosLista;
import utils.PasswordUtilidades;

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
    /**
     * Intenta iniciar sesión con las credenciales proporcionadas.
     * Solo permite iniciar sesión si no hay una sesión activa actualmente.
     *
     * @param usuario Nombre de usuario para iniciar sesión
     * @param password Contraseña del usuario
     * @return true si el inicio de sesión fue exitoso, false en caso contrario
     */
    public boolean iniciarSesion(String usuario, String password){
        boolean resultado = false;
        SesionUsuario sesion=SesionUsuario.getInstance();

        if(sesion.getUsuarioActual() == null && usuario != null && password != null) {
            for(Usuario u: usuarios.getUsuarios()){
                if(u.getUsuario()!=null && u.getPassword()!=null && u.getUsuario().equals(usuario) && PasswordUtilidades.checkPassword(password,u.getPassword())){
                    sesion.iniciarSesion(u);
                    resultado = true;
                    break;
                }
            }
        }
        return resultado;
    }

    /**
     * Cierra la sesión del usuario actual.
     * @return true si se cerró la sesión correctamente, false si no había sesión iniciada.
     */
    public boolean cerrarSesion() {
        boolean resultado = false;
        SesionUsuario sesion = SesionUsuario.getInstance();
        if (sesion.getUsuarioActual() != null) {
            sesion.cerrarSesion();
            resultado = true;
        }
        return resultado;
    }

    /**
     * Actualiza una usuario existente.
     * @param usuario La usuario a actualizar
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizar(Usuario usuario) {
        boolean flag = false;
        if(usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }
        Usuario antiguo = null;
        for (Usuario i : usuarios.getUsuarios()) {
            if (i.getUsuario().equals(usuario.getUsuario())) {
                antiguo = i;
                break;
            }
        }

        if (antiguo != null) {
            usuarios.removeUsuario(antiguo);
            usuarios.addUsuario(usuario);
            XMLManager.writeXML(usuarios,"usuarios.xml");
            return true;
        }

        return flag;
    }
}
