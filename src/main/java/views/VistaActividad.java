package views;

import exceptions.FechaNoValidaException;
import model.Actividad;
import utils.UtilidadesGenerales;

import java.util.List;
import java.util.Scanner;

public class VistaActividad {
    public static Scanner sc = new Scanner(System.in);
    public static Actividad registrarActividad(){
        Actividad nuevaActividad=null;

        System.out.print("Introduzca nombre de la actividad: ");
        String nombre = sc.nextLine();
        System.out.print("Introduzca descripcion de la actividad: ");
        String descripcion = sc.nextLine();

        String fechaInicio = pideFecha("Introduzca la fecha de inicio de la actividad (dd/mm/yyyy): ");
        String fechaFin = pideFecha("Introduzca la fecha de fin de la actividad (dd/mm/yyyy): ");

        nuevaActividad=new Actividad(nombre, descripcion, fechaInicio, fechaFin);
        return nuevaActividad;
    }
    public static String pideFecha(String msn){
        String fecha="";
        boolean flag=false;

        do{
            System.out.print(msn);
            fecha = sc.nextLine();
            try{
                if(!UtilidadesGenerales.validaFormatoFecha(fecha)){
                    throw new FechaNoValidaException("Debe introducir el fecha correctamente, con un formato dd/mm/yyyy.");
                }
                flag=true;
            }catch(FechaNoValidaException e){
                System.out.println(e.getMessage());
            }
        }while(!flag);
        return fecha;
    }
    public static void mostrarActividades(List<Actividad> actividades) {
        if (actividades.isEmpty()) {
            System.out.println("No hay actividades disponibles.");
            return;
        }

        System.out.println("===== Lista de Actividades =====");

        int numero = 1; // Para numerar las actividades
        for (Actividad actividad : actividades) {
            System.out.println(numero + ". " + actividad.getNombre() + " - " + actividad.getDescripcion());
            numero++;
        }
    }

}
