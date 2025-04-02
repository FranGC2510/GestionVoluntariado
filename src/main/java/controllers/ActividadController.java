package controllers;

import dataAccess.XMLManager;
import exceptions.ActividadExisteException;
import exceptions.ActividadNoEncontradaException;
import exceptions.IniciativaNoEncontradaException;
import interfaces.Gestionable;
import model.Actividad;
import model.ActividadLista;
import model.Iniciativa;
import model.Voluntario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Controlador que gestiona las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * relacionadas con las actividades en el sistema. Implementa la interfaz Gestionable
 * para proporcionar operaciones estándar de gestión de actividades.
 */
public class ActividadController implements Gestionable <Actividad> {
    private ActividadLista actividades;
    /**
     * Constructor del controlador de actividades.
     */
    public ActividadController() {
        this.actividades = XMLManager.readXML(new ActividadLista(),"actividades.xml");
        if(actividades == null){
            actividades = new ActividadLista();
        }
    }

    /**
     * Crea una nueva actividad si no existe otra.
     * @param actividad La actividad a crear.
     * @throws IllegalArgumentException Si la actividad es nula.
     * @throws ActividadExisteException Si ya existe una actividad.
     * @return true si la actividad fue creada con éxito, false en caso contrario.
     */
    @Override
    public boolean crear(Actividad actividad) {
        boolean flag = false;
        if (actividad == null) {
            throw new IllegalArgumentException("La actividad no puede ser nula");
        }
        if (actividades.containsActividad(actividad)) {
            throw new ActividadExisteException("Actividad con ID " + actividad.getId() + " ya existe.");
        }
        if (actividades.addActividad(actividad)) {
            XMLManager.writeXML(actividades, "actividades.xml");
            flag = true;
        }
        return flag;
    }

    /**
     * Busca una actividad por su ID.
     * @param id El ID de la actividad a buscar.
     * @throws IllegalArgumentException Si el ID es nulo.
     * @return La actividad encontrada, null si no se encuentra.
     */
    @Override
    public Actividad buscarPorId(String id) {
        Actividad actividad = null;
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        for (Actividad a : actividades.getActividades()) {
            if (a.getId().equals(id)) {
                actividad = a;
                break;
            }
        }
        return actividad;
    }

    /**
     *  Elimina una actividad, en el caso de que no tenga voluntarios asignados.
     * @param actividad Actividad que se quiere eliminar.
     * @throws IllegalArgumentException Si la actividad es nula.
     * @throws ActividadNoEncontradaException Si no se encuentra la actividad especificada.
     * @return True en caso de eliminarse, false en caso contrario.
     */
    @Override
    public boolean eliminar(Actividad actividad) {
        boolean eliminar = false;
        if (actividad == null) {
            throw new IllegalArgumentException("La actividad no puede ser nula");
        }
        if (!actividades.containsActividad(actividad)) {
            throw new ActividadNoEncontradaException("Actividad con ID " + actividad.getId() + " no encontrada.");
        }
        if (actividades.removeActividad(actividad) && actividad.getVoluntarios().isEmpty()) {
            XMLManager.writeXML(actividades, "actividades.xml");
            eliminar = true;
        }
        return eliminar;
    }

    /**
     * Lista todas las actividades actuales.
     * @return Una lista de todas las actividades.
     */
    @Override
    public List<Actividad> listar() {
        return new ArrayList<>(actividades.getActividades());
    }

    /**
     * Añade un voluntario a una actividad específica.
     * Si el voluntario no está ya asignado a la actividad, se añade y se actualiza
     * la persistencia XML.
     *
     * @param actividad La actividad a la que se añadirá el voluntario
     * @param voluntario El voluntario que se añadirá a la actividad
     * @throws IllegalArgumentException Si la actividad o el voluntario son nulos
     * @return true si el voluntario fue añadido exitosamente, false si ya estaba asignado
     */
    public boolean addVoluntario(Actividad actividad, Voluntario voluntario) {
        boolean flag = false;
        if (actividad == null) {
            throw new IllegalArgumentException("La actividad no puede ser nula");
        }
        if (voluntario == null) {
            throw new IllegalArgumentException("El voluntario no puede ser nulo");
        }
        HashSet<Voluntario> voluntarios = actividad.getVoluntarios();
        if(!voluntarios.contains(voluntario)){
            voluntarios.add(voluntario);
            actividad.setVoluntarios(voluntarios);
            XMLManager.writeXML(actividades, "actividades.xml");
            flag = true;
        }
        return flag;
    }

    /**
     * Actualiza una actividad existente.
     * @param actividad La actividad a actualizar
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizar(Actividad actividad) {
        boolean flag = false;
        if(actividad == null) {
            throw new IllegalArgumentException("La actividad no puede ser nula");
        }
        Actividad antigua = null;
        for (Actividad i : actividades.getActividades()) {
            if (i.getId().equals(actividad.getId())) {
                antigua = i;
                break;
            }
        }

        if (antigua != null) {
            actividades.removeActividad(antigua);
            actividades.addActividad(actividad);
            XMLManager.writeXML(actividades,"actividades.xml");
            return true;
        }

        return flag;
    }
}

