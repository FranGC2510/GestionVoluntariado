package utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Adaptador JAXB para convertir LocalDate a String y viceversa durante la serialización XML.
 */
public class AdaptadorLocalDateXml extends XmlAdapter<String, LocalDate> {
    private static final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @Override
    public LocalDate unmarshal(String v) throws Exception {
        LocalDate fecha;
        if (v == null || v.trim().isEmpty()) {
            fecha=null;
        }else{
            fecha = LocalDate.parse(v, formato);
        }
        return fecha;
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        String fecha;
        if (v == null) {
            fecha="";
        }else{
            fecha=v.format(formato);
        }
        return fecha;
    }
}
