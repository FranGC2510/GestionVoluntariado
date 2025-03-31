package controllers;

import dataAccess.XMLManager;
import exceptions.ActividadExisteException;
import exceptions.ActividadNoEncontradaException;
import exceptions.UsuarioNoEncontradoException;
import interfaces.Gestionable;
import model.Actividad;
import model.ActividadLista;
import model.Voluntario;

import java.util.ArrayList;
import java.util.List;

public class ActividadController implements Gestionable <Actividad> {
    private ActividadLista actividades;
    private UsuarioController usuarioController;
    /**
     * Constructor del controlador de actividades.
     */
    public ActividadController() {
        this.actividades = XMLManager.readXML(new ActividadLista(),"actividades.xml");
        if(actividades == null){
            actividades = new ActividadLista();
        }
        this.usuarioController = new UsuarioController();
    }

    /**
     * Crea una nueva actividad si no existe otra.
     * @param actividad La actividad a crear.
     * @throws ActividadExisteException Si ya existe una actividad.
     */
    @Override
    public boolean crear(Actividad actividad) {
        boolean flag = false;
        if(actividad!=null){
            if(actividades.containsActividad(actividad)){
                throw new ActividadExisteException("Iniciativa con ID " + actividad.getId() + " ya existe.");
            }
            if(actividades.addActividad(actividad)){
                XMLManager.writeXML(actividades,"actividades.xml");
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public Actividad buscarPorId(String id) {
        Actividad actividad = null;
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
     * @throws ActividadNoEncontradaException Si no se encuentra la actividad especificada.
     * @return True en caso de eliminarse, false en caso contrario.
     */
    @Override
    public boolean eliminar(Actividad actividad) {
        boolean eliminar = false;
        if(actividad!=null){
            if(!actividades.containsActividad(actividad)){
                throw new ActividadNoEncontradaException("Actividad con ID " + actividad.getId() + " no ha sido encontrada.");
            }
            if(actividad.getVoluntarios().isEmpty() && actividades.removeActividad(actividad)){
                XMLManager.writeXML(actividades,"actividades.xml");
                eliminar = true;
            }
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


    public void agregarVoluntarioActividad(String idActividad, String nombreUsuario) {
        Actividad actividad = buscarPorId(idActividad);
        if (actividad == null) {
            throw new ActividadNoEncontradaException("Actividad con ID " + idActividad + " no encontrada.");
        }

        Voluntario voluntario = usuarioController.buscarVoluntarioPorNombre(nombreUsuario);

        if (voluntario == null) {
            throw new UsuarioNoEncontradoException("El voluntario con usuario '" + nombreUsuario + "' no existe.");
        }

        try {
            actividad.agregarVoluntario(voluntario);
            XMLManager.writeXML(actividades, "actividades.xml");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}

