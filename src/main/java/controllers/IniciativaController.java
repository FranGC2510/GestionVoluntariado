package controllers;

import dataAccess.XMLManager;
import exceptions.ActividadExisteException;
import interfaces.Gestionable;
import exceptions.IniciativaNoEncontradaException;
import exceptions.IniciativaExisteException;
import model.Actividad;
import model.Iniciativa;
import model.IniciativaLista;
import model.UsuariosLista;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

// Clase que implementa la gestion de iniciativas mediante operaciones CRUD
public class IniciativaController implements Gestionable<Iniciativa> {

    // Lista que almacena las iniciativas existentes
    private IniciativaLista iniciativas;

    /**
     * Constructor del controlador de iniciativas.
     */
    public IniciativaController() {
        this.iniciativas = XMLManager.readXML(new IniciativaLista(),"iniciativas.xml");
        if(iniciativas == null){
            iniciativas = new IniciativaLista();
        }
    }

    /**
     * Crea una nueva iniciativa si no existe otra con el mismo ID.
     * @param iniciativa La iniciativa a crear.
     * @throws IniciativaExisteException Si ya existe una iniciativa con el mismo ID.
     */
    @Override
    public boolean crear(Iniciativa iniciativa) {
        boolean flag = false;
        if(iniciativa!=null){
            if(iniciativas.addIniciativa(iniciativa)){
                XMLManager.writeXML(iniciativas,"iniciativas.xml");
                flag = true;
            }
        }
        return flag;
    }

    /**
     * Busca una iniciativa por su ID.
     * @param id El ID de la iniciativa a buscar
     * @return La iniciativa encontrada
     * @throws IniciativaNoEncontradaException Si no se encuentra una iniciativa con el ID especificado
     */
    @Override
    public Iniciativa buscarPorId(String id) {
        Iniciativa iniciativaEncontrada = null;
        if(id==null){
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        for(Iniciativa ini : iniciativas.getIniciativas()) {
            if(ini.getId().equals(id)) {
                iniciativaEncontrada = ini;
                break;
            }
        }
        return iniciativaEncontrada;
    }

    /**
     *  Elimina una iniciativa, en el caso de que no tenga actividades asignadas.
     * @param iniciativa Iniciativa que se quiere eliminar.
     * @throws IniciativaNoEncontradaException Si no se encuentra una iniciativa con el ID especificado.
     * @return True en caso de eliminarse, false en caso contrario.
     */
    @Override
    public boolean eliminar(Iniciativa iniciativa) {
        boolean eliminar = false;
        if(iniciativa!=null){
            if(!iniciativas.containsIniciativa(iniciativa)){
                throw new IniciativaNoEncontradaException("Iniciativa con ID " + iniciativa.getId() + " no ha sido encontrada.");
            }
            if(iniciativa.getActividades().isEmpty() && iniciativas.removeIniciativa(iniciativa)){
                XMLManager.writeXML(iniciativas,"iniciativas.xml");
                eliminar = true;
            }
        }
        return eliminar;
    }

    /**
     * Lista todas las iniciativas actuales.
     * @return Una lista de todas las iniciativas.
     */
    @Override
    public List<Iniciativa> listar() {
        return new ArrayList<>(iniciativas.getIniciativas()); // Devuelve una copia para evitar modificaciones externas
    }

    public List<Actividad> listarActividadesByIniciativa(String id) {
        Iniciativa iniciativa=buscarPorId(id);
        return iniciativa.getActividades();
    }

    public boolean addActividad(String id,Actividad actividad) {
        boolean flag = false;
        Iniciativa iniciativa=buscarPorId(id);
        if(iniciativa!=null){
            iniciativa.getActividades().add(actividad);
            XMLManager.writeXML(iniciativas,"iniciativas.xml");
            flag = true;
        }
        return flag;
    }

    public boolean removeActividad(String id,Actividad actividad) {
        boolean flag = false;
        Iniciativa iniciativa=buscarPorId(id);
        if(iniciativa!=null){
            iniciativa.getActividades().remove(actividad);
            XMLManager.writeXML(iniciativas,"iniciativas.xml");
            flag = true;
        }
        return flag;
    }
    /**
     * Actualiza una iniciativa existente.
     * @param iniciativa La iniciativa a actualizar
     * @return true si la actualización fue exitosa, false en caso contrario
     * @throws IniciativaNoEncontradaException si la iniciativa no existe
     */
    public boolean actualizar(Iniciativa iniciativa) {
        boolean flag = false;
        if(iniciativa == null) {
            throw new IllegalArgumentException("La iniciativa no puede ser nula");
        }
        Iniciativa antigua = null;
        for (Iniciativa i : iniciativas.getIniciativas()) {
            if (i.getId().equals(iniciativa.getId())) {
                antigua = i;
                break;
            }
        }

        if (antigua != null) {
            iniciativas.removeIniciativa(antigua);
            iniciativas.addIniciativa(iniciativa);
            XMLManager.writeXML(iniciativas,"iniciativas.xml");
            return true;
        }

        return flag;
    }
}