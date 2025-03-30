package interfaces;

import java.util.List;

public interface Gestionable<T> {
    boolean crear(T t);
    T leer(int id);
    boolean actualizar(T t);
    boolean eliminar(int id);
    List<T> listar();
}