package views;

import exceptions.EmailInvalidoException;
import exceptions.PasswordInvalidaException;
import model.Creador;
import model.Usuario;
import model.Voluntario;
import utils.PasswordUtilidades;
import utils.UtilidadesGenerales;

import java.util.Scanner;

public class VistaUsuario {
    public static Scanner sc = new Scanner(System.in);
    /**
     * Registra un nuevo usuario, solicitando su nombre, correo electrónico, nombre de usuario y contraseña.
     * @param tipo El tipo de usuario que se desea registrar:
     *             1 - Creador, 2 - Voluntario.
     * @return Un objeto {@link Usuario} del tipo especificado (Creador o Voluntario).
     */
    public static Usuario registroUsuario(int tipo){
        Usuario nuevoUsuario=null;
        boolean flag=false;

        System.out.print("Introduzca nombre y apellido: ");
        String nombre = sc.nextLine();
        String email = pideEmail();
        String usuario = pideUsuario();
        String password = pidePassword();
        do{
            System.out.print("Vuelva a introducir la misma contraseña: ");
            String contrasena = sc.nextLine();
            if(contrasena.equals(password)){
                flag=true;
            }else {
                System.out.println("Debe introducir la misma contraseña para confirmar.");
            }
        }while (!flag);

        String hashPassword = PasswordUtilidades.hashPassword(password);

        switch(tipo){
            case 1:
                System.out.print("Introduzca su ONG afiliada: ");
                String ONG = sc.nextLine();
                nuevoUsuario=new Creador(nombre,usuario,hashPassword,email,ONG);
                break;
            case 2:
                nuevoUsuario=new Voluntario(nombre,usuario,hashPassword,email);
                break;
        }

        return nuevoUsuario;
    }
    /**
     * Muestra las opciones de registro disponibles para el usuario y devuelve la opción seleccionada.
     * @return La opción seleccionada por el usuario para el tipo de registro:
     *         1 - Registrarse como creador.
     *         2 - Registrarse como voluntario.
     */
    public static int tipoRegistro() {
        int opcion=0;
        String menuRegistrar="""
                ===================================================
                Selecciona cómo deseas registrarte:
                ===================================================
                
                          1. Registrarse como creador
                          2. Registrarse como voluntario
                ---------------------------------------------------
                """;

        opcion= UtilidadesGenerales.pideEntero(menuRegistrar,1,2);
        return opcion;
    }
    /**
     * Solicita al usuario que introduzca un correo electrónico válido.
     * @return El correo electrónico introducido por el usuario.
     * @throws EmailInvalidoException Si el correo electrónico no es válido según el formato establecido.
     */
    public static String pideEmail(){
        String email="";
        boolean flag=false;

        do{
            System.out.print("Introduzca el email: ");
            email = sc.nextLine();
            try{
                if(!UtilidadesGenerales.validaEmail(email)){
                    throw new EmailInvalidoException("Debe introducir el email correctamente.");
                }
                flag=true;
            }catch(EmailInvalidoException e){
                System.out.println(e.getMessage());
            }
        }while(!flag);
        return email;
    }
    /**
     * Solicita al usuario que introduzca una contraseña válida.
     * @return La contraseña introducida por el usuario.
     * @throws PasswordInvalidaException Si la contraseña no cumple con los requisitos de seguridad.
     */
    public static String pidePassword(){
        String password="";
        boolean flag=false;

        do{
            System.out.print("Introduzca contraseña: ");
            password = sc.nextLine();
            try{
                if(!PasswordUtilidades.validaPassword(password)){
                    throw new PasswordInvalidaException("La contraseña debe tener al menos 8 caracteres, de los cuales " +
                            "al menos una mayuscula, una minuscula y un número.");
                }
                flag=true;
            }catch(PasswordInvalidaException e){
                System.out.println(e.getMessage());
            }
        }while(!flag);
        return password;
    }

    public static String pideUsuario(){
        String usuario="";
        System.out.print("Introduzca nombre de usuario: ");
        usuario = sc.nextLine();
        return usuario;
    }
}
