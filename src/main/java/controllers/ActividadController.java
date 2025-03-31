package controllers;

import dataAccess.XMLManager;
import exceptions.ActividadExisteException;
import exceptions.ActividadNoEncontradaException;
import exceptions.IniciativaExisteException;
import exceptions.IniciativaNoEncontradaException;
import interfaces.Gestionable;
import model.Actividad;
import model.Iniciativa;
import model.Voluntario;

import java.util.ArrayList;
import java.util.List;

public class ActividadController implements Gestionable <Actividad> {
    private List<Actividad> actividades;

    public ActividadController(List<Actividad> actividades) {
        this.actividades = actividades;
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
            if(actividades.contains(actividad)){
                throw new ActividadExisteException("Iniciativa con ID " + actividad.getId() + " ya existe.");
            }
            if(actividades.add(actividad)){
                /*
                 * Try catch:
                 * XMLManager.writeXML(actividades,"actividades.xml"); guarda la lista de actividades en un archivo XML.
                 * Si ocurre un error, se lanza la excepción.
                 * e.printStackTrace(); imprime la traza de la excepción en la consola, ayudando a identificar el error.
                 */
                try {
                    // XMLManager.writeXML(actividades,"actividades.xml");
                    flag = true;
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
        return flag;
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
            if(!actividades.contains(actividad)){
                throw new ActividadNoEncontradaException("Actividad con ID " + actividad.getId() + " no ha sido encontrada.");
            }
            if(actividad.getVoluntarios().isEmpty() && actividades.remove(actividad)){
                /*
                 * Try catch:
                 * XMLManager.writeXML(actividades,"actividades.xml"); guarda la lista de actividades en un archivo XML.
                 * Si ocurre un error, se lanza la excepción.
                 * e.printStackTrace(); imprime la traza de la excepción en la consola, ayudando a identificar el error.
                 */
                try {
                    // XMLManager.writeXML(actividades,"actividades.xml");
                    eliminar = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }

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
        return new ArrayList<>(actividades); // Devuelve una copia para evitar modificaciones externas
    }
}

