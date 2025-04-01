package views;

import utils.UtilidadesGenerales;

import java.util.Scanner;

public class VistaBienvenida {
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
}
