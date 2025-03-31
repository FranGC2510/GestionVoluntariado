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

    /**
     * Comprueba si una fecha tiene el formato correcto 'dd/mm/yyyy' y si es válida.
     * Primero verifica el formato con una expresión regular y luego revisa si el día existe en ese mes y año.
     * @param fecha La fecha en formato 'dd/mm/yyyy'.
     * @return true si la fecha es válida, false si no lo es.
     */
    public static boolean validaFecha(String fecha) {
        return fecha.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$") &&
                esDiaValido(Integer.parseInt(fecha.substring(0, 2)),
                        Integer.parseInt(fecha.substring(3, 5)),
                        Integer.parseInt(fecha.substring(6, 10)));
    }

    /**
     * Comprueba si un día es válido según el mes y el año.
     * @param dia Día de la fecha.
     * @param mes Mes de la fecha.
     * @param anio Año de la fecha.
     * @return true si el día es válido, false si no lo es.
     */
    public static boolean esDiaValido(int dia, int mes, int anio) {
        boolean resultado = false;

        if (mes == 2) {
            if (esAnioBisiesto(anio)) {
                resultado = dia >= 1 && dia <= 29;
            } else {
                resultado = dia >= 1 && dia <= 28;
            }
        } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            resultado = dia >= 1 && dia <= 30;
        } else {
            resultado = dia >= 1 && dia <= 31;
        }

        return resultado;
    }

    /**
     * Comprueba si un año es bisiesto.
     * Un año es bisiesto si es divisible por 4, pero no por 100, a menos que también sea divisible por 400.
     * @param anio Año a comprobar.
     * @return true si el año es bisiesto, false si no lo es.
     */
    public static boolean esAnioBisiesto(int anio) {
        return (anio % 4 == 0 && (anio % 100 != 0 || anio % 400 == 0));
    }
}
