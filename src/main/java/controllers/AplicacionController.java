package controllers;

import model.Actividad;
import model.Creador;
import model.Iniciativa;
import model.Usuario;
import views.VistaActividad;
import views.VistaIniciativa;
import views.VistaMenu;
import views.VistaUsuario;

import java.util.List;

/**
 * Controlador principal de la aplicación.
 * Maneja el flujo principal del programa y la coordinación entre otros controladores.
 */
public class AplicacionController {
    private final IniciativaController iniciativaController;
    private final ActividadController actividadController;
    private UsuarioController usuarioController;
    private Usuario usuarioActual;

    public AplicacionController() {
        this.iniciativaController = new IniciativaController();
        this.actividadController = new ActividadController();
        this.usuarioController = new UsuarioController();
        this.usuarioActual = null;
    }

    /**
     * Inicia la ejecución de la aplicación.
     */
    public void iniciar() {
        boolean ejecutar = true;

        while (ejecutar) {
            int opcion = VistaMenu.menuPrincipal();
            switch (opcion) {
                case 1: // Iniciar Sesión
                    if (usuarioController.iniciarSesion(VistaUsuario.pideUsuario(),VistaUsuario.pidePassword())) {
                        usuarioActual = SesionUsuario.getInstance().getUsuarioActual();
                        if (SesionUsuario.getInstance().getUsuarioActual().getClass().equals(Creador.class)) { // Si es creador
                            gestionarMenuCreador();
                        } else if (usuarioActual.getClass().getName().equals("Voluntario")) { // Si es voluntario
                            //gestionarMenuVoluntario();
                        }
                    }else{
                        System.out.println("Usuario no encontrado");
                    }
                    break;
                case 2: // Registrarse
                    registrarUsuario();
                    break;
                case 0: // Salir
                    ejecutar = false;
                    break;
            }
        }
    }

    /**
     * Gestiona el proceso de registro de un nuevo usuario.
     */
    private void registrarUsuario() {
        int tipoUsuario = VistaMenu.tipoRegistro();
        Usuario nuevoUsuario = VistaUsuario.registroUsuario(tipoUsuario);
        if (usuarioController.addUsuario(nuevoUsuario)) {
            usuarioActual = nuevoUsuario;
            if (tipoUsuario == 1) { // Si es creador
                gestionarMenuCreador();
            } else if (tipoUsuario == 2) { // Si es voluntario
                //gestionarMenuVoluntario();
            }
        }
    }

    /**
     * Gestiona el menú principal del creador de iniciativas.
     */
    private void gestionarMenuCreador() {
        boolean continuar = true;
        while (continuar) {
            int opcion = VistaMenu.menuCreador();
            switch (opcion) {
                case 1: // Crear iniciativa
                    Iniciativa nuevaIniciativa = VistaIniciativa.registrarIniciativa();
                    iniciativaController.crear(nuevaIniciativa);
                    break;
                case 2: // Ver todas las iniciativas
                    List<Iniciativa> iniciativas = iniciativaController.listar();
                    VistaIniciativa.listadoIniciativas(iniciativas);
                    break;
                case 3: // Ver en detalle una iniciativa
                    gestionarVistaDetalleIniciativa();
                    break;
                case 4: // Cerrar sesión
                    continuar = false;
                    break;
            }
        }
    }

    /**
     * Gestiona la vista detallada de una iniciativa y sus operaciones.
     */
    private void gestionarVistaDetalleIniciativa() {
        String id = VistaIniciativa.pideIdIniciativa();
        Iniciativa iniciativa = iniciativaController.buscarPorId(id);
        if (iniciativa != null) {
            VistaIniciativa.mostrarDetalleIniciativa(iniciativa);
            gestionarOperacionesIniciativa(iniciativa);
        }
    }

    /**
     * Gestiona las operaciones disponibles para una iniciativa específica.
     * @param iniciativa La iniciativa sobre la que se realizarán las operaciones.
     */
    private void gestionarOperacionesIniciativa(Iniciativa iniciativa) {
        boolean continuar = true;
        while (continuar) {
            int opcion = VistaMenu.menuIniciativa();
            switch (opcion) {
                case 1: // Ver actividades en detalle
                    List<Actividad> actividades = iniciativaController.listarActividadesByIniciativa(iniciativa.getId());
                    VistaActividad.mostrarDetallesActividades(actividades);
                    break;
                case 2: // Añadir actividad
                    Actividad nuevaActividad = VistaActividad.registrarActividad();
                    if(actividadController.crear(nuevaActividad)){
                        iniciativaController.addActividad(iniciativa.getId(), nuevaActividad);
                    }
                    break;
                case 3: // Eliminar actividad
                    String idActividad = VistaActividad.pideIdActividad();
                    Actividad actividad = actividadController.buscarPorId(idActividad);
                    if (actividad != null) {
                        if (actividadController.eliminar(actividad)) {
                            iniciativaController.removeActividad(iniciativa.getId(), actividad);
                        }
                    }
                    break;
                case 4: // Eliminar iniciativa
                    if(iniciativa.getActividades().isEmpty()){
                        iniciativaController.eliminar(iniciativa);
                    }else{
                        System.out.println("La iniciativa no se puede eliminar, tiene actividades asignadas.");
                    }
                    break;
                case 5: // Volver al menú principal
                    continuar = false;
                    break;
            }
        }
    }
}
