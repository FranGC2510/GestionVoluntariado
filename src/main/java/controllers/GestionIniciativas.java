package controllers;

import interfaces.Gestionable;
import exceptions.IniciativaNoEncontradaException;
import exceptions.IniciativaExisteException;
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
     * @throws IniciativaExisteException Si ya existe una iniciativa con el mismo ID.
     */
    @Override
    public boolean crear(Iniciativa iniciativa) {
        boolean existe = true;
        for (Iniciativa ini : iniciativas) {
            if (ini.getId() == iniciativa.getId()) {
                existe = false;
                throw new IniciativaExisteException("Iniciativa con ID " + iniciativa.getId() + " ya existe.");
            }
        }
        iniciativas.add(iniciativa);
        return existe;
    }

    /**
     * Lee y retorna una iniciativa por su ID.
     * @param id El ID de la iniciativa a leer.
     * @return La iniciativa encontrada.
     * @throws IniciativaNoEncontradaException Si no se encuentra una iniciativa con el ID especificado.
     */
    @Override
    public Iniciativa leer(int id) {
        for (Iniciativa iniciativa : iniciativas) {
            if (iniciativa.getId() == id) {
                return iniciativa;
            }
        }
        throw new IniciativaNoEncontradaException("Iniciativa con ID " + id + " no ha sido encontrada.");
    }

    /**
     * Actualiza una iniciativa existente.
     * @param iniciativa La iniciativa con los datos actualizados.
     * @throws IniciativaNoEncontradaException Si no se encuentra una iniciativa con el ID especificado.
     */
    @Override
    public boolean actualizar(Iniciativa iniciativa) {
        boolean flag = false;
        for (int i = 0; i < iniciativas.size(); i++) {
            if (iniciativas.get(i).getId() == iniciativa.getId()) {
                iniciativas.set(i, iniciativa);
                flag = true;
                break;
            }
        }
        if (!flag) {
            throw new IniciativaNoEncontradaException("Iniciativa con ID " + iniciativa.getId() + " no ha sido encontrada.");
        }
        return flag;
    }

    /**
     * Elimina una iniciativa por su ID.
     * @param id El ID de la iniciativa a eliminar.
     * @throws IniciativaNoEncontradaException Si no se encuentra una iniciativa con el ID especificado.
     */
    @Override
    public boolean eliminar(int id) {
        boolean eliminar = iniciativas.removeIf(iniciativa -> iniciativa.getId() == id);
        if (!eliminar) {
            throw new IniciativaNoEncontradaException("Iniciativa con ID " + id + " no ha sido encontrada.");
        }
        return eliminar;
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