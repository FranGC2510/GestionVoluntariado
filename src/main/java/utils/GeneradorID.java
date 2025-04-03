package utils;

import dataAccess.XMLManager;
import model.IdsLista;

import java.util.UUID;

/**
 * Clase utilitaria que proporciona funcionalidad para generar identificadores únicos (IDs)
 * para las entidades del sistema. Los IDs generados son persistentes y se almacenan en XML.
 *
 * Características de los IDs generados:
 * - Son únicos en todo el sistema
 * - Tienen una longitud de 3 caracteres
 * - Se basan en UUIDs aleatorios
 * - Se almacenan en un archivo XML para garantizar su unicidad entre sesiones
 */
public class GeneradorID {

    /**
     * Genera un nuevo identificador único que no existe en el sistema.
     * El proceso es el siguiente:
     * 1. Carga los IDs existentes desde el archivo XML
     * 2. Genera un nuevo UUID y toma los primeros 3 caracteres
     * 3. Verifica que no exista en la lista de IDs
     * 4. Si existe, repite el proceso hasta obtener uno único
     * 5. Guarda el nuevo ID en el archivo XML
     *
     * @return Un nuevo identificador único de 3 caracteres
     */
    public static String generarID() {
        IdsLista idsGenerados= XMLManager.readXML(new IdsLista(),"Ids.xml");
        String nuevoID;
        do{
            nuevoID = UUID.randomUUID().toString().substring(0,3).replace("-","");
        }while(idsGenerados.containsIds(nuevoID));
        idsGenerados.addIds(nuevoID);
        XMLManager.writeXML(idsGenerados,"Ids.xml");
        return nuevoID;
    }
}
