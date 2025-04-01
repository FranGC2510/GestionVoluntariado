package views;

import model.Iniciativa;
import utils.UtilidadesGenerales;

import java.util.List;
import java.util.Scanner;

public class VistaIniciativa {
    public static Scanner sc = new Scanner(System.in);
    public static int menuCreador(){
        int opcion=0;
        String diseyoAscii="""
                ===================================================
                         BIENVENIDO, CREADOR DE INICIATIVAS
                ===================================================

                         ¡Gracias por contribuir con nuevas
                        oportunidades para la comunidad!

                ---------------------------------------------------
                  1. Crear una nueva iniciativa
                  2. Ver todas mis iniciativas
                  3. Añadir actividades a una iniciativa
                  4. Eliminar una iniciativa
                  5. Cerrar sesión
                ---------------------------------------------------
                """;
        System.out.println(diseyoAscii);

        return UtilidadesGenerales.pideEntero("Elija una opción: ",1,5);
    }
    public static Iniciativa registrarIniciativa(){
        Iniciativa nuevaIniciativa=null;

        System.out.print("Introduzca nombre de la iniciativa: ");
        String nombre = sc.nextLine();
        System.out.print("Introduzca descripción de la iniciativa: ");
        String descripcion = sc.nextLine();

        nuevaIniciativa=new Iniciativa(nombre, descripcion);
        return nuevaIniciativa;
    }
    public static void listadoIniciativas(List<Iniciativa> iniciativas){
        for(Iniciativa iniciativa:iniciativas){
            System.out.println(iniciativa);
        }
    }
}
