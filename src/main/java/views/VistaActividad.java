package views;

import exceptions.FechaNoValidaException;
import model.Actividad;
import utils.UtilidadesGenerales;

import java.util.List;
import java.util.Scanner;

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

        String fechaInicio = pideFecha("Introduzca la fecha de inicio de la actividad (dd/mm/yyyy): ");
        String fechaFin = pideFecha("Introduzca la fecha de fin de la actividad (dd/mm/yyyy): ");

        return new Actividad(nombre, descripcion, fechaInicio, fechaFin);
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
}
