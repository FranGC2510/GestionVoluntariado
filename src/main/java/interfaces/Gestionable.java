package interfaces;

import java.util.List;

public interface Gestionable<T> {
    void crear(T t);
    T leer(int id);
    void actualizar(T t);
    void eliminar(int id);
    List<T> listar();
}