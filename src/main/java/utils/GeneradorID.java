package utils;

import dataAccess.XMLManager;
import model.IdsLista;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class GeneradorID {

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
