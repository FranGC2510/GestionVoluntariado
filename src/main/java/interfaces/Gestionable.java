package interfaces;

import java.util.List;

public interface Gestionable<T> {
    boolean crear(T t);
    T buscarPorId(String id);
    boolean eliminar(T t);
    List<T> listar();
}