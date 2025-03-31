package interfaces;

import java.util.HashSet;
import java.util.List;

public interface Gestionable<T> {
    boolean crear(T t);
   /* T leer(int id);*/
    boolean eliminar(T t);
    List<T> listar();
}