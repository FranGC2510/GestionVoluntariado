package controllers;

import dataAccess.XMLManager;
import exceptions.FechaNoValidaException;
import interfaces.Estado;
import model.*;
import views.*;

import java.util.List;

/**
 * Controlador principal de la aplicación.
 * Maneja el flujo principal del programa y la coordinación entre otros controladores.
 */
public class AplicacionController {
    private IniciativaController iniciativaController;
    private ActividadController actividadController;
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
                    int tipo=VistaMenu.tipoInicio();
                    if (usuarioController.iniciarSesion(VistaUsuario.pideUsuario(), VistaUsuario.pidePassword(),tipo)) {
                        usuarioActual = SesionUsuario.getInstance().getUsuarioActual();
                        if (usuarioActual instanceof Creador) { // Si es creador
                            gestionarMenuCreador();
                        } else if (usuarioActual instanceof Voluntario) { // Si es voluntario
                            gestionarMenuVoluntario();
                        }
                    } else {
                        VistaComun.mostrarMensaje("Usuario no valido");
                    }
                    break;
                case 2: // RegistrarsE
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
            SesionUsuario.getInstance().setUsuarioActual(nuevoUsuario);
            usuarioActual = SesionUsuario.getInstance().getUsuarioActual();
            if (tipoUsuario == 1) { // Si es creador
                gestionarMenuCreador();
            } else if (tipoUsuario == 2) { // Si es voluntario0

                gestionarMenuVoluntario();
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
                    usuarioController.cerrarSesion();
                    usuarioActual = null;
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
        }else{
            VistaComun.mostrarMensaje("Iniciativa no encontrada.");
        }
    }

    /**
     * Gestiona las operaciones disponibles para una iniciativa específica.
     *
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
                    try{
                        Actividad nuevaActividad = VistaActividad.registrarActividad();
                        if (actividadController.crear(nuevaActividad)) {
                            iniciativaController.addActividad(iniciativa.getId(), nuevaActividad);
                        }
                    }catch(FechaNoValidaException e){
                        VistaComun.mostrarMensaje(e.getMessage());
                    }

                    break;
                case 3: // Eliminar actividad
                    String idActividad = VistaActividad.pideIdActividad("Introduzca el ID de la actividad que desea eliminar: ");
                    Actividad actividad = actividadController.buscarPorId(idActividad);
                    if (actividad != null) {
                        if (actividadController.eliminar(actividad)) {
                            iniciativaController.removeActividad(iniciativa.getId(), actividad);
                        }
                    }
                    break;
                case 4: // Eliminar iniciativa
                    if (iniciativa.getActividades().isEmpty()) {
                        iniciativaController.eliminar(iniciativa);
                        VistaComun.mostrarMensaje("La iniciativa ha sido eliminada correctamente.");
                    } else {
                        VistaComun.mostrarMensaje("La iniciativa no se puede eliminar, tiene actividades asignadas.");
                    }
                    continuar = false;
                    break;
                case 5: // Volver al menú principal
                    continuar = false;
                    break;
            }
        }
    }

    /**
     * Gestiona el menú principal del voluntario.
     * Muestra las opciones disponibles para el voluntario y procesa sus selecciones,
     * incluyendo ver actividades disponibles, ver actividades asignadas y cerrar sesión.
     */
    private void gestionarMenuVoluntario() {
        boolean continuar = true;
        while (continuar) {
            int opcion = VistaMenu.menuVoluntario();
            switch (opcion) {
                case 1 :// Ver actividades disponibles (PENDIENTES o EN_CURSO)
                    actividadesDisponibles();
                    break;
                case 2 :// Ver mis actividades asignadas
                    actividadesAsignadas();
                    break;
                case 3 :// Cerrar sesión
                    usuarioController.cerrarSesion();
                    usuarioActual = null;
                    continuar = false;
                    break;
            }
        }
    }

    /**
     * Muestra las actividades disponibles para los voluntarios.
     * Lista todas las iniciativas y sus actividades que están en estado PENDIENTE o EN_CURSO,
     * permitiendo a los voluntarios unirse a las actividades que les interesen.
     */
    private void actividadesDisponibles() {
        List<Iniciativa> iniciativas = iniciativaController.listar();
        boolean hayActividades = false;
        if (iniciativas != null && !iniciativas.isEmpty()) {
            VistaComun.mostrarMensaje("\nActividades disponibles:");
            for (Iniciativa iniciativa : iniciativas) {
                List<Actividad> actividades = iniciativa.getActividades();
                if (actividades != null && !actividades.isEmpty()) {
                    boolean mostrarIniciativa = false;
                    String actividadesDisponibles = "";

                    for (Actividad actividad : actividades) {
                        if (actividad.getEstado() == Estado.PENDIENTE ||
                                actividad.getEstado() == Estado.EN_CURSO &&
                                        !actividad.getVoluntarios().contains((Voluntario) usuarioActual)) {
                            if (!mostrarIniciativa) {
                                actividadesDisponibles = "\nIniciativa: " +
                                        iniciativa.getNombre() + "\n";
                                mostrarIniciativa = true;
                            }
                            actividadesDisponibles += "- " + actividad.getNombre() +
                                    " (ID: " + actividad.getId() +
                                    ", Estado: " + actividad.getEstado() +
                                    ")\n";
                            hayActividades = true;
                        }
                    }
                    if (mostrarIniciativa) {
                        VistaComun.mostrarMensaje(actividadesDisponibles);
                    }
                }
            }
            if (hayActividades) {
                asignarActividad(iniciativas);
            }
        } else {
            VistaComun.mostrarMensaje("No hay iniciativas ni actividades disponibles.");
        }
    }

    private void asignarActividad(List<Iniciativa> iniciativas) {
        String idActividad = VistaActividad.pideIdActividad("\n¿Desea asignarse a alguna actividad? Introduzca el ID de la actividad (o 0 para cancelar):");
        if (!idActividad.equals("0")) {
            Actividad actividad = actividadController.buscarPorId(idActividad);

            if (actividad != null) {
                Voluntario voluntario = (Voluntario) SesionUsuario.getInstance().getUsuarioActual();
                boolean actividadActualizada = false;
                try {
                    for (Iniciativa iniciativa : iniciativas) {
                        List<Actividad> actividades = iniciativa.getActividades();
                        for (int i = 0; i < actividades.size(); i++) {
                            if (actividades.get(i).getId().equals(idActividad)) {
                                actividades.set(i, actividad);
                                iniciativaController.actualizar(iniciativa);
                                actividadActualizada = true;
                                break;
                            }
                        }
                        if (actividadActualizada) break;
                    }
                    if (actividadActualizada && actividadController.addVoluntario(actividad, voluntario)) {
                        VistaComun.mostrarMensaje("Te has asignado correctamente a la actividad: " + actividad.getNombre());
                    } else {
                        VistaComun.mostrarMensaje("No se pudo asignar el voluntario a la actividad.");
                    }
                } catch (Exception e) {
                    VistaComun.mostrarMensaje("Error al asignar el voluntario: " + e.getMessage());
                }
            } else {
                VistaComun.mostrarMensaje("No se encontró ninguna actividad con ese ID.");
            }
        }
    }

    /**
     * Muestra las actividades a las que está asignado el voluntario actual.
     * Recorre todas las iniciativas y muestra aquellas actividades donde el voluntario
     * actual está participando, incluyendo su estado y otros detalles relevantes.
     */
    private void actividadesAsignadas() {
        List<Iniciativa> iniciativas = iniciativaController.listar();
        boolean hayActividades = false;
        Voluntario voluntario = (Voluntario) SesionUsuario.getInstance().getUsuarioActual();

        if (iniciativas != null && !iniciativas.isEmpty()) {
            VistaComun.mostrarMensaje("\nTus actividades asignadas:");
            for (Iniciativa iniciativa : iniciativas) {
                List<Actividad> actividades = iniciativa.getActividades();
                if (actividades != null && !actividades.isEmpty()) {
                    for (Actividad actividad : actividades) {
                        if (actividad.getVoluntarios().contains(voluntario)) {
                            VistaActividad.mostrarDetalleActividad(actividad, iniciativa);
                            hayActividades = true;
                        }
                    }
                }
            }

            if (hayActividades) {
                modificarEstadoActividad(iniciativas);
            }else{
                VistaComun.mostrarMensaje("No tienes actividades asignadas.");
            }
        } else {
            VistaComun.mostrarMensaje("No hay iniciativas registradas.");
        }
    }
    private void modificarEstadoActividad(List<Iniciativa> iniciativas) {
        String idActividad = VistaActividad.pideIdActividad("\n¿Desea modificar el estado de alguna actividad? Introduzca el ID (o 0 para cancelar):");
        if (!idActividad.equals("0")) {
            Actividad actividad = actividadController.buscarPorId(idActividad);
            Voluntario voluntario = (Voluntario) SesionUsuario.getInstance().getUsuarioActual();

            if (actividad != null && actividad.getVoluntarios().contains(voluntario)) {
                Estado nuevoEstado = VistaActividad.pideEstado();
                if (nuevoEstado != null) {
                    boolean actividadActualizada = false;
                    try {
                        actividad.setEstado(nuevoEstado);
                        if (nuevoEstado == Estado.FINALIZADA) {
                            String comentario = VistaActividad.pideComentarioFinalizacion();
                            actividad.setComentario(comentario);
                            for (Voluntario vol : actividad.getVoluntarios()) {
                                vol.sumarPuntos(100);
                                usuarioController.actualizar(vol);
                            }
                            VistaComun.mostrarMensaje("Se han sumado 100 puntos a todos los voluntarios de la actividad. ");
                        }
                        actividadController.actualizar(actividad);
                        for (Iniciativa iniciativa : iniciativas) {
                            List<Actividad> actividades = iniciativa.getActividades();
                            for (int i = 0; i < actividades.size(); i++) {
                                if (actividades.get(i).getId().equals(idActividad)) {
                                    actividades.set(i, actividad);
                                    iniciativaController.actualizar(iniciativa);
                                    actividadActualizada = true;
                                    break;
                                }
                            }
                            if (actividadActualizada) {
                                break;
                            }
                        }

                        if (actividadActualizada) {
                            VistaComun.mostrarMensaje("Estado de la actividad actualizado correctamente.");
                        } else {
                            VistaComun.mostrarMensaje("No se pudo actualizar el estado de la actividad.");
                        }
                    } catch (Exception e) {
                        VistaComun.mostrarMensaje("Error al actualizar el estado: " + e.getMessage());
                    }
                }
            } else {
                VistaComun.mostrarMensaje("Actividad no encontrada o no estás asignado a ella.");
            }
        }
    }
}
