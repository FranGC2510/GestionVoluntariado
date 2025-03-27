package views;

import exceptions.EmailInvalidoException;
import exceptions.PasswordInvalidaException;
import model.Creador;
import model.Usuario;
import model.Voluntario;
import utils.PasswordUtilidades;
import utils.UtilidadesGenerales;

import java.util.Scanner;

public class VistaConsola {
    public static Scanner sc = new Scanner(System.in);
    /**
     * Muestra un mensaje en la consola.
     * @param msn El mensaje que se mostrará al usuario.
     */
    public static void mostrarMensaje(String msn){
        System.out.println(msn);
    }

    /**
     * Muestra la pantalla de bienvenida y permite al usuario seleccionar una opción.
     * @return La opción seleccionada por el usuario.
     *         1 - Iniciar sesión.
     *         2 - Registrarse.
     *         0 - Salir.
     */
    public static int bienvenida() {
        int opcion=-1;
        String asciiArt = """
                ===================================================
                  GESTIÓN DE VOLUNTARIADO Y ACTIVIDADES SOCIALES
                ===================================================
                
                         ,@@@@@@@,
                 ,,,.   ,@@@@@@/@@,  .oo8888o.
              ,&%%&%&&%,@@@@@/@@@@@@,8888\\88/8o
             ,%&\\%&&%&&%,@@@\\@@@/@@@88\\88888/88'
             %&&%&%&/%&&%@\\@@/ /@@@88888\\88888'
             %&&%/ %&%%&&@@\\ V /@@' `88\\8 `/88'
             `&%\\ ` /%&'    |.|        \\ '|8'
                 |o|        | |         | |
                 |.|        | |         | |
               \\/ ._\\//_/__/  ,\\_//__\\/.  \\_//__/
                
                ---------------------------------------------------
                                1. Iniciar Sesión
                                2. Registrarse
                                0. Salir
                ---------------------------------------------------
                """;

        System.out.println(asciiArt);

        opcion= UtilidadesGenerales.pideEntero("Elija una opción: ", 0,2);
        switch(opcion){
            case 1:
                System.out.println("Redigiriendose a Iniciar sesión...");
                break;
                case 2:
                    System.out.println("Redigiriendose a Registrarse...");
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
            }
        return opcion;
    }

    /**
     * Registra un nuevo usuario, solicitando su nombre, correo electrónico, nombre de usuario y contraseña.
     * @param tipo El tipo de usuario que se desea registrar:
     *             1 - Creador, 2 - Voluntario.
     * @return Un objeto {@link Usuario} del tipo especificado (Creador o Voluntario).
     */
    public static Usuario registro(int tipo){
        Usuario nuevoUsuario=null;
        boolean flag=false;

        System.out.print("Introduzca nombre y apellido: ");
        String nombre = sc.nextLine();
        String email = pideEmail();
        System.out.println("Introduzca nombre de usuario: ");
        String usuario = sc.nextLine();
        String password = pidePassword();
        do{
            System.out.println("Vuelva a introducir la misma contraseña: ");
            String contrasena = sc.nextLine();
            if(contrasena.equals(password)){
                flag=true;
            }else {
                System.out.println("Debe introducir la misma contraseña para confirmar.");
            }
        }while (!flag);

        String hashPassword = PasswordUtilidades.hashPassword(password);

        switch(tipo){
            case 1:
                System.out.println("Introduzca su ONG afiliada: ");
                String ONG = sc.nextLine();
                nuevoUsuario=new Creador(nombre,usuario,hashPassword,email,ONG);
                break;
            case 2:
                nuevoUsuario=new Voluntario(nombre,usuario,hashPassword,email);
                break;
            case 3:

        }

        return nuevoUsuario;
    }

    /**
     * Muestra las opciones de registro disponibles para el usuario y devuelve la opción seleccionada.
     * @return La opción seleccionada por el usuario para el tipo de registro:
     *         1 - Registrarse como creador.
     *         2 - Registrarse como voluntario.
     *         3 - Registrarse como ambos.
     */
    public static int tipoRegistro() {
        int opcion=0;
        String menuRegistrar="""
                ===================================================
                Selecciona cómo deseas registrarte:
                ===================================================
                
                          1. Registrarse como creador
                          2. Registrarse como voluntario
                          3. Registrarse como ambos
                ---------------------------------------------------
                """;

        opcion=UtilidadesGenerales.pideEntero(menuRegistrar,1,3);
        return opcion;
    }
    /**
     * Solicita al usuario que introduzca un correo electrónico válido.
     * @return El correo electrónico introducido por el usuario.
     * @throws EmailInvalidoException Si el correo electrónico no es válido según el formato establecido.
     */
    public static String pideEmail(){
        String email="";
        boolean flag=false;

        do{
            System.out.print("Introduzca el email: ");
            email = sc.nextLine();
            try{
                if(!UtilidadesGenerales.validaEmail(email)){
                    throw new EmailInvalidoException("Debe introducir el email correctamente.");
                }
                flag=true;
            }catch(EmailInvalidoException e){
                System.out.println(e.getMessage());
            }
        }while(!flag);
        return email;
    }
    /**
     * Solicita al usuario que introduzca una contraseña válida.
     * @return La contraseña introducida por el usuario.
     * @throws PasswordInvalidaException Si la contraseña no cumple con los requisitos de seguridad.
     */
    public static String pidePassword(){
        String password="";
        boolean flag=false;

        do{
            System.out.print("Introduzca contraseña: ");
            password = sc.nextLine();
            try{
                if(!PasswordUtilidades.validaPassword(password)){
                    throw new PasswordInvalidaException("La contraseña debe tener al menos 8 caracteres, de los cuales " +
                            "al menos una mayuscula, una minuscula y un número.");
                }
                flag=true;
            }catch(PasswordInvalidaException e){
                System.out.println(e.getMessage());
            }
        }while(!flag);
        return password;
    }
}
