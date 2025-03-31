package utils;

import views.VistaConsola;

import java.util.InputMismatchException;
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
    /**
     * Solicita al usuario un número entero dentro de un rango especificado. Y comprueba que el
     * número introducido sea un entero y se encuentre en dicho rango.
     * @param msn El mensaje que se mostrará al usuario para indicar qué debe introducir.
     * @param valorMin El valor mínimo permitido para el entero.
     * @param valorMax El valor máximo permitido para el entero.
     * @return Un número entero válido dentro del rango especificado.
     */
    public static int pideEntero(String msn, int valorMin, int valorMax){
        int entero=0;
        boolean flag=false;
        do{
            try{
                VistaConsola.mostrarMensaje(msn);
                entero=teclado.nextInt();
                if(comprobarRango(entero,valorMin,valorMax)){
                    flag=true;
                }else {
                    VistaConsola.mostrarMensaje("Debes introducir un valor entre "+valorMin+" y "+valorMax+".");
                }
            }catch (InputMismatchException e){
                VistaConsola.mostrarMensaje("Error. Tipo de dato incorrecto.");
                teclado.next();
            }
        }while(!flag);
        return entero;
    }
    /**
     * Comprueba si un número entero está dentro de un rango especificado.
     * @param valor El entero a verificar.
     * @param min El límite inferior del rango.
     * @param max El límite superior del rango.
     * @return Devuelve true si el valor está dentro del rango inclusive, false en caso contrario.
     */
    private static boolean comprobarRango(int valor, int min, int max){
        boolean flag=false;
        if(valor>=min&&valor<=max){
            flag=true;
        }
        return flag;
    }
    /**
     * Valida si una dirección de correo electrónico es válida utilizando una expresión regular.
     *
     * Este método verifica que el correo electrónico proporcionado cumpla con un formato estándar
     * de dirección de correo, asegurándose de que contenga caracteres alfanuméricos, seguido de un "@"
     * y un dominio, con un sufijo de dominio válido (por ejemplo, ".com", ".org", etc.).
     *
     * @param email La dirección de correo electrónico a validar.
     * @return true si el correo electrónico tiene un formato válido, false en caso contrario.
     */
    public static boolean validaEmail(String email){
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    public static boolean validaFormatoFecha(String fecha){
        return fecha.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$");
    }
}
