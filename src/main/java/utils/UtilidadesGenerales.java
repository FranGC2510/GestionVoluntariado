package utils;

import views.VistaConsola;

import java.util.Scanner;

public class UtilidadesGenerales {
    public static Scanner teclado = new Scanner(System.in);
    /**
     * Solicita al usuario una cadena de texto.
     * @param msn El mensaje que se mostrará al usuario para indicar qué debe introducir.
     * @return La cadena de texto introducida.
     */
    public static String pideString(String msn){
        VistaConsola.mostrarMensaje(msn+" ");
        return teclado.next();
    }
}
