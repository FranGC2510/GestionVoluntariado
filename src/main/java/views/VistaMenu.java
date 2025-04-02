package views;

import utils.UtilidadesGenerales;

public class VistaMenu {
    /**
     * Muestra la pantalla de bienvenida y permite al usuario seleccionar una opción.
     * @return La opción seleccionada por el usuario.
     *         1 - Iniciar sesión.
     *         2 - Registrarse.
     *         0 - Salir.
     */
    public static int menuPrincipal() {
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

        int opcion = UtilidadesGenerales.pideEntero("Elija una opción: ", 0,2);
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
     * Muestra las opciones de registro disponibles para el usuario y devuelve la opción seleccionada.
     * @return La opción seleccionada por el usuario para el tipo de registro:
     *         1 - Registrarse como creador.
     *         2 - Registrarse como voluntario.
     */
    public static int tipoRegistro() {
        String menuRegistrar = """
                ===================================================
                Selecciona cómo deseas registrarte:
                ===================================================
                
                          1. Registrarse como creador
                          2. Registrarse como voluntario
                ---------------------------------------------------
                """;

        return UtilidadesGenerales.pideEntero(menuRegistrar,1,2);
    }

    /**
     * Muestra el menú principal del creador y sus opciones disponibles.
     * @return La opción seleccionada por el usuario.
     */
    public static int menuCreador() {
        String diseyoAscii = """
                ===================================================
                         BIENVENIDO, CREADOR DE INICIATIVAS
                ===================================================

                         ¡Gracias por contribuir con nuevas
                        oportunidades para la comunidad!

                ---------------------------------------------------
                  1. Crear una nueva iniciativa
                  2. Ver todas mis iniciativas
                  3. Ver en detalle una iniciativa
                  4. Cerrar sesión
                ---------------------------------------------------
                """;
        System.out.println(diseyoAscii);

        return UtilidadesGenerales.pideEntero("Elija una opción: ",1,4);
    }

    /**
     * Muestra el menú de opciones disponibles para una iniciativa específica.
     * Este menú aparece después de mostrar los detalles de una iniciativa al creador.
     * @return La opción seleccionada por el usuario.
     */
    public static int menuIniciativa() {
        String diseyoAscii = """
                ===================================================
                              OPCIONES DE INICIATIVA
                ===================================================

                ---------------------------------------------------
                  1. Ver actividades en detalle
                  2. Añadir actividad
                  3. Eliminar actividad
                  4. Eliminar iniciativa
                  5. Volver al menú principal
                ---------------------------------------------------
                """;
        System.out.println(diseyoAscii);

        return UtilidadesGenerales.pideEntero("Elija una opción: ",1,5);
    }

    /**
     * Muestra el menú principal del voluntario y sus opciones disponibles.
     * @return La opción seleccionada por el usuario.
     */
    public static int menuVoluntario() {
        String diseyoAscii = """
                ===================================================
                         BIENVENIDO, VOLUNTARIO
                ===================================================

                         ¡Gracias por tu interés en contribuir
                          con la comunidad!

                ---------------------------------------------------
                  1. Ver actividades disponibles
                  2. Ver mis actividades
                  3. Cerrar sesión
                ---------------------------------------------------
                """;
        System.out.println(diseyoAscii);

        return UtilidadesGenerales.pideEntero("Elija una opción: ",1,3);
    }
}

