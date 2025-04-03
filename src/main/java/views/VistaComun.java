package views;

/**
 * Clase utilitaria que proporciona funcionalidades comunes de interfaz de usuario
 * que son utilizadas por las demás vistas del sistema.
 *
 * Esta clase centraliza operaciones básicas de UI como:
 * - Mostrar mensajes al usuario
 * - Formatear la salida de texto
 */
public class VistaComun {
    /**
     * Muestra un mensaje en la consola.
     * @param msn El mensaje que se mostrará al usuario.
     */
    public static void mostrarMensaje(String msn) {
        System.out.println(msn);
    }
}
