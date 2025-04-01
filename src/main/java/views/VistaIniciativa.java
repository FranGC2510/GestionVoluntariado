package views;

import model.Iniciativa;

import java.util.List;
import java.util.Scanner;

public class VistaIniciativa {
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Registra una nueva iniciativa solicitando sus datos al usuario.
     * @return La nueva iniciativa creada.
     */
    public static Iniciativa registrarIniciativa() {
        System.out.print("Introduzca nombre de la iniciativa: ");
        String nombre = sc.nextLine();
        System.out.print("Introduzca descripción de la iniciativa: ");
        String descripcion = sc.nextLine();

        return new Iniciativa(nombre, descripcion);
    }

    /**
     * Muestra un listado de todas las iniciativas.
     * @param iniciativas Lista de iniciativas a mostrar.
     */
    public static void listadoIniciativas(List<Iniciativa> iniciativas) {
        if (iniciativas == null || iniciativas.isEmpty()) {
            System.out.println("No hay iniciativas registradas.");
        }else{
            System.out.println("\nListado de iniciativas:");
            for(Iniciativa iniciativa : iniciativas) {
                System.out.println(iniciativa);
            }
        }
    }

    /**
     * Solicita al usuario que introduzca el ID de una iniciativa.
     * @return El ID de la iniciativa introducido por el usuario.
     */
    public static String pideIdIniciativa() {
        System.out.print("Introduzca el ID de la iniciativa: ");
        return sc.nextLine();
    }

    /**
     * Muestra los detalles completos de una iniciativa.
     * Incluye su ID, nombre, descripción, estado, actividades y voluntarios.
     * @param iniciativa La iniciativa cuyos detalles se mostrarán.
     */
    public static void mostrarDetalleIniciativa(Iniciativa iniciativa) {
        if (iniciativa == null) {
            System.out.println("La iniciativa no existe.");
            return;
        }

        System.out.println("""
                ===================================================
                              DETALLE DE INICIATIVA
                ===================================================
                """);
        System.out.println(iniciativa);
        System.out.println("""
                ===================================================
                """);
    }
}
