import controllers.UsuarioController;
import dataAccess.XMLManager;
import model.Creador;
import model.Usuario;
import model.UsuariosLista;
import model.Voluntario;
import views.VistaConsola;

public class TestXML {
    public static void main(String[] args) {
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
        UsuarioController controlador=new UsuarioController(listaUsuarios);
        int opcion;
        do{
            opcion= VistaConsola.bienvenida();
            switch (opcion){
                case 2:
                    if(controlador.addUsuario()){
                        System.out.println("Usuario agregado");
                    }else{
                        System.out.println("Usuario no agregado");
                    }
                    break;
            }
        }while(opcion==0);

    }
}
