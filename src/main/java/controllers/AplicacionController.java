package controllers;

import exceptions.FechaNoValidaException;
import interfaces.Estado;
import model.*;
import views.*;

import java.util.List;

/**
 * Controlador principal de la aplicación que coordina todas las operaciones del sistema.
 * Actúa como punto central de control entre las vistas y los demás controladores,
 * gestionando el flujo de la aplicación, la autenticación de usuarios y las operaciones
 * específicas para cada tipo de usuario (Creador y Voluntario).
 */
public class AplicacionController {
    private IniciativaController iniciativaController;
    private ActividadController actividadController;
    private UsuarioController usuarioController;
    private Usuario usuarioActual;

    /**
     * Controlador principal de la aplicación.
     * Maneja el flujo principal del programa y la coordinación entre otros controladores.
     */
    public AplicacionController() {
        this.iniciativaController = new IniciativaController();
        this.actividadController = new ActividadController();
        this.usuarioController = new UsuarioController();
        this.usuarioActual = null;
    }

    /**
     * Inicia la ejecución de la aplicación y gestiona el flujo principal del programa.
     * Muestra el menú principal y maneja las opciones de:
     * - Inicio de sesión (diferenciando entre Creador y Voluntario)
     * - Registro de nuevos usuarios
     * - Salida del sistema
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
     * Gestiona el proceso de registro de un nuevo usuario en el sistema.
     * Permite registrar tanto Creadores como Voluntarios, solicitando los datos
     * necesarios según el tipo de usuario seleccionado. Tras un registro exitoso,
     * inicia automáticamente la sesión del nuevo usuario y lo dirige al menú
     * correspondiente a su rol.
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
     * Proporciona acceso a las operaciones específicas del rol Creador:
     * - Crear nuevas iniciativas
     * - Ver listado de todas las iniciativas
     * - Ver detalles de una iniciativa específica
     * - Cerrar sesión
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
     * Gestiona la vista detallada de una iniciativa y sus operaciones asociadas.
     * Permite al usuario buscar una iniciativa por su ID y, si existe,
     * muestra sus detalles y permite realizar operaciones sobre ella.
     * Si la iniciativa no se encuentra, muestra un mensaje de error.
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
     * Gestiona el menú principal del voluntario y sus operaciones disponibles.
     * Proporciona acceso a las funcionalidades específicas del rol Voluntario:
     * - Ver actividades disponibles para participar
     * - Ver actividades asignadas al voluntario
     * - Actualizar el estado de sus actividades
     * - Cerrar sesión
     *
     * El menú se mantiene activo hasta que el voluntario decide cerrar sesión.
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

    /**
     * Gestiona el proceso de asignación de un voluntario a una actividad.
     * Permite al voluntario unirse a una actividad específica si:
     * - La actividad existe
     * - El voluntario no está ya asignado a ella
     * - La actividad está en estado PENDIENTE o EN_CURSO
     *
     * @param iniciativas Lista de iniciativas disponibles en el sistema
     */
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
     * Muestra y gestiona las actividades asignadas al voluntario actual.
     * Funcionalidades:
     * - Lista todas las actividades donde el voluntario está participando
     * - Muestra el estado actual de cada actividad
     * - Permite modificar el estado de las actividades
     * - Gestiona la finalización de actividades y la asignación de puntos
     *
     * Si el voluntario no tiene actividades asignadas, muestra un mensaje informativo.
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

    /**
     * Permite a un voluntario modificar el estado de una actividad en la que participa.
     * Funcionalidades:
     * - Validación de pertenencia del voluntario a la actividad
     * - Actualización del estado de la actividad
     * - Gestión especial para actividades FINALIZADAS:
     *   - Solicitud de comentario de finalización
     *   - Asignación de 100 puntos a todos los voluntarios participantes
     * - Persistencia de los cambios en el sistema
     *
     * @param iniciativas Lista de iniciativas donde buscar la actividad a modificar
     */
    private void modificarEstadoActividad(List<Iniciativa> iniciativas) {
        String idActividad = VistaActividad.pideIdActividad("\n¿Desea modificar el estado de alguna actividad? Introduzca el ID (o 0 para cancelar):");
        if (!idActividad.equals("0")) {
            Actividad actividad = actividadController.buscarPorId(idActividad);
            Voluntario voluntario = (Voluntario) SesionUsuario.getInstance().getUsuarioActual();

            if (actividad != null && actividad.getVoluntarios().contains(voluntario) && !actividad.getEstado().equals(Estado.FINALIZADA)) {
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
                VistaComun.mostrarMensaje("El estado de la actividad no se puede modificar.");
            }
        }
    }
}
