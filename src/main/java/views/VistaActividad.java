package views;

import exceptions.FechaNoValidaException;
import interfaces.Estado;
import model.Actividad;
import model.Iniciativa;
import utils.UtilidadesGenerales;

import java.util.List;
import java.util.Scanner;

/**
 * Clase que gestiona la interfaz de usuario para las operaciones relacionadas con actividades.
 * Proporciona métodos para:
 * - Registro y creación de nuevas actividades
 * - Visualización de detalles de actividades
 * - Gestión de estados de actividades
 *
 * Esta clase actúa como intermediaria entre el usuario y el sistema,
 * asegurando que los datos introducidos sean válidos antes de procesarlos.
 */
public class VistaActividad {
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Registra una nueva actividad solicitando sus datos al usuario.
     * @return La nueva actividad creada.
     */
    public static Actividad registrarActividad() {
        System.out.print("Introduzca nombre de la actividad: ");
        String nombre = sc.nextLine();
        System.out.print("Introduzca descripcion de la actividad: ");
        String descripcion = sc.nextLine();

        Actividad actividad = null;
        boolean fechasValidas = false;

        while (!fechasValidas) {
            try {
                String fechaInicio = pideFecha("Introduzca la fecha de inicio de la actividad (dd/mm/yyyy): ");
                String fechaFin = pideFecha("Introduzca la fecha de fin de la actividad (dd/mm/yyyy): ");
                actividad = new Actividad(nombre, descripcion, fechaInicio, fechaFin);
                fechasValidas = true;
            } catch (FechaNoValidaException e) {
                System.out.println(e.getMessage());
            }
        }

        return actividad;
    }

    /**
     * Solicita al usuario que introduzca una fecha válida.
     * @param msn El mensaje a mostrar al usuario.
     * @return La fecha introducida en formato String.
     */
    public static String pideFecha(String msn) {
        String fecha = "";
        boolean flag = false;

        do {
            System.out.print(msn);
            fecha = sc.nextLine();
            try {
                if(!UtilidadesGenerales.validaFormatoFecha(fecha)) {
                    throw new FechaNoValidaException("Debe introducir el fecha correctamente, con un formato dd/mm/yyyy.");
                }
                flag = true;
            } catch(FechaNoValidaException e) {
                System.out.println(e.getMessage());
            }
        } while(!flag);
        return fecha;
    }

    /**
     * Solicita al usuario que introduzca el ID de una actividad.
     * @return El ID de la actividad introducido por el usuario.
     */
    public static String pideIdActividad(String msn) {
        System.out.print(msn);
        return sc.nextLine();
    }

    /**
     * Muestra un listado detallado de todas las actividades.
     * @param actividades Lista de actividades a mostrar.
     */
    public static void mostrarDetallesActividades(List<Actividad> actividades) {
        if (actividades == null || actividades.isEmpty()) {
            System.out.println("No hay actividades registradas en esta iniciativa.");
        } else {
            System.out.println("\nListado de actividades:");
            for(Actividad actividad : actividades) {
                System.out.println(actividad);
            }
        }
    }

    /**
     * Muestra los detalles completos de una actividad.
     * Incluye su ID, nombre, descripción, estado, fechas y voluntarios asignados.
     * @param actividad La actividad cuyos detalles se mostrarán.
     */
    public static void mostrarDetalleActividad(Actividad actividad, Iniciativa iniciativa) {
        if (actividad == null) {
            System.out.println("La actividad no existe.");
            return;
        }

        System.out.println("""
                ===================================================
                              DETALLE DE ACTIVIDAD
                ===================================================
                """);
        System.out.println("Iniciativa: "+iniciativa.getId() + "||" + iniciativa.getNombre());
        System.out.println(actividad);
        System.out.println("""
                ===================================================
                """);
    }

    /**
     * Muestra los estados disponibles y permite al usuario seleccionar uno.
     * @return El estado seleccionado o null si la selección no es válida
     */
    public static Estado pideEstado() {
        Estado estado = null;
        System.out.println("""
                \nEstados disponibles:
                1. PENDIENTE
                2. EN_CURSO
                3. FINALIZADA""");

        int opcion = UtilidadesGenerales.pideEntero("Seleccione el nuevo estado: ",1,3);
        switch (opcion) {
            case 1 -> estado=Estado.PENDIENTE;
            case 2 -> estado=Estado.EN_CURSO;
            case 3 -> estado=Estado.FINALIZADA;
        }
        return estado;
    }
    /**
     * Solicita al usuario un comentario de finalización para una actividad.
     * @return El comentario introducido por el usuario.
     */
    public static String pideComentarioFinalizacion() {
        System.out.println("""
                \n===================================================
                       COMENTARIO DE FINALIZACIÓN
                Describe brevemente los resultados o
                   conclusiones de esta actividad
                ===================================================
                """);
        System.out.print("Comentario: ");
        return sc.nextLine();
    }
}
