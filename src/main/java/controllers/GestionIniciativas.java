package controllers;

import interfaces.Gestionable;
import exceptions.IniciativaNotFoundException;
import exceptions.IniciativaAlreadyExistsException;
import model.Iniciativa;

import java.util.ArrayList;
import java.util.List;

// Clase que implementa la gestion de iniciativas mediante operaciones CRUD
public class GestionIniciativas implements Gestionable<Iniciativa> {

    // Lista que almacena las iniciativas existentes
    private List<Iniciativa> iniciativas = new ArrayList<>();

    /**
     * Crea una nueva iniciativa si no existe otra con el mismo ID.
     * @param iniciativa La iniciativa a crear.
     * @throws IniciativaAlreadyExistsException Si ya existe una iniciativa con el mismo ID.
     */
    @Override
    public void crear(Iniciativa iniciativa) {
        for (Iniciativa ini : iniciativas) {
            if (ini.getId() == iniciativa.getId()) {
                throw new IniciativaAlreadyExistsException("Iniciativa con ID " + iniciativa.getId() + " ya existe.");
            }
        }
        iniciativas.add(iniciativa);
    }

    /**
     * Lee y retorna una iniciativa por su ID.
     * @param id El ID de la iniciativa a leer.
     * @return La iniciativa encontrada.
     * @throws IniciativaNotFoundException Si no se encuentra una iniciativa con el ID especificado.
     */
    @Override
    public Iniciativa leer(int id) {
        for (Iniciativa iniciativa : iniciativas) {
            if (iniciativa.getId() == id) {
                return iniciativa;
            }
        }
        throw new IniciativaNotFoundException("Iniciativa con ID " + id + " no ha sido encontrada.");
    }

    /**
     * Actualiza una iniciativa existente.
     * @param iniciativa La iniciativa con los datos actualizados.
     * @throws IniciativaNotFoundException Si no se encuentra una iniciativa con el ID especificado.
     */
    @Override
    public void actualizar(Iniciativa iniciativa) {
        for (int i = 0; i < iniciativas.size(); i++) {
            if (iniciativas.get(i).getId() == iniciativa.getId()) {
                iniciativas.set(i, iniciativa);
                return;
            }
        }
        throw new IniciativaNotFoundException("Iniciativa con ID " + iniciativa.getId() + " no ha sido encontrada.");
    }

    /**
     * Elimina una iniciativa por su ID.
     * @param id El ID de la iniciativa a eliminar.
     * @throws IniciativaNotFoundException Si no se encuentra una iniciativa con el ID especificado.
     */
    @Override
    public void eliminar(int id) {
        boolean removed = iniciativas.removeIf(iniciativa -> iniciativa.getId() == id);
        if (!removed) {
            throw new IniciativaNotFoundException("Iniciativa con ID " + id + " no ha sido encontrada.");
        }
    }

    /**
     * Lista todas las iniciativas actuales.
     * @return Una lista de todas las iniciativas.
     */
    @Override
    public List<Iniciativa> listar() {
        return new ArrayList<>(iniciativas); // Devuelve una copia para evitar modificaciones externas
    }
}