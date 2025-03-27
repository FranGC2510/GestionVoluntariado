package views;

import model.Creador;
import model.Usuario;
import model.Voluntario;
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

    public static Usuario registro(int tipo){
        Usuario nuevoUsuario=null;
        boolean flag=false;

        System.out.print("Introduzca nombre y apellido: ");
        String nombre = sc.nextLine();
        System.out.print("Introduzca email: ");
        String email = sc.nextLine();
        System.out.println("Introduzca nombre de usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Introduzca contraseña: ");
        String password = sc.nextLine();
        do{
            System.out.println("Vuelva a introducir la misma contraseña: ");
            String contrasena = sc.nextLine();
            if(contrasena.equals(password)){
                flag=true;
            }else {
                System.out.println("Debe introducir la misma contraseña para confirmar.");
            }
        }while (!flag);

        switch(tipo){
            case 1:
                System.out.println("Introduzca su ONG afiliada: ");
                String ONG = sc.nextLine();
                nuevoUsuario=new Creador(nombre,usuario,password,email,ONG);
                break;
            case 2:
                nuevoUsuario=new Voluntario(nombre,usuario,password,email);
                break;
            case 3:

        }

        return nuevoUsuario;
    }

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
}
