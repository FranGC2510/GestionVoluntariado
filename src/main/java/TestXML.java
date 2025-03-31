import controllers.IniciativaController;
import controllers.SesionUsuario;
import controllers.UsuarioController;
import dataAccess.XMLManager;
import model.Creador;
import model.Usuario;
import model.UsuariosLista;
import model.Voluntario;
import views.VistaConsola;

public class TestXML {
    public static void main(String[] args) {
        /*
        Usuario usuario1=new Creador("Miguel","miguelillo","1234","ghdjd@jh.com","PepeCalvo");
        Usuario usuario2=new Voluntario("Paco", "paquillo", "1234", "hola@gmail.es");

        UsuariosLista listaUsuarios = new UsuariosLista();
        listaUsuarios.addUsuario(usuario1);
        listaUsuarios.addUsuario(usuario2);
        XMLManager.writeXML(listaUsuarios,"usuarios.xml");
        /*
        UsuariosLista listaUsuarios2 = new UsuariosLista();

        listaUsuarios2=XMLManager.readXML(listaUsuarios2,"usuarios.xml");

        if (listaUsuarios2!=null){
            System.out.println(listaUsuarios2);
        }
         */
        UsuarioController controladorUsu=new UsuarioController();
        IniciativaController controladorIni=new IniciativaController();
        int opcion;
        do{
            opcion= VistaConsola.bienvenida();
            switch (opcion){
                case 1:
                    if(controladorUsu.iniciarSesion()){
                        System.out.println("Iniciando sesion...\n");
                        if(SesionUsuario.getInstance().getUsuarioActual().getClass().equals(Creador.class)){
                            int opc=VistaConsola.menuCreador();
                            switch (opc){
                                case 1:
                                    if(controladorIni.crear(VistaConsola.registrarIniciativa())){
                                        System.out.println("Registrado exitosamente.\n");
                                    }else{
                                        System.out.println("Error al registrar la iniciativa.\n");
                                    }
                                    break;
                                case 2:
                                    VistaConsola.listadoIniciativas(controladorIni.listar());
                                    break;
                                /* HARIA FALTA UN METODO DE BUSCAR LA INICIATIVA POR ID
                            case 4:
                                if(controladorIni.eliminar())

                                 */
                            }
                        }

                    }else{
                        System.out.println("Sus credenciales no coinciden con ningunas de nuestra base de datos. " +
                                "Por favor ingrese de nuevo sus datos o registrese.\n");
                    }
                    break;
                case 2:
                    if(controladorUsu.addUsuario(VistaConsola.registroUsuario(VistaConsola.tipoRegistro()))){
                        System.out.println("Usuario agregado");
                    }else{
                        System.out.println("Usuario no agregado");
                    }
                    break;
            }
        }while(opcion!=0);

    }
}
