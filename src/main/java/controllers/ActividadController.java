package controllers;

import dataAccess.XMLManager;
import exceptions.ActividadExisteException;
import exceptions.ActividadNoEncontradaException;
import interfaces.Gestionable;
import model.Actividad;
import model.ActividadLista;
import model.Voluntario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
}

