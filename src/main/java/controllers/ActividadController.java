package controllers;

import model.Actividad;
import model.Voluntario;

import java.util.ArrayList;
import java.util.List;

public class ActividadController {
    private List<Actividad> actividades;

    public ActividadController(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    public List<Actividad> listarActividadesAsignadas(Voluntario voluntario) {
        List<Actividad> actividadesAsignadas = new ArrayList<>();
        for (Actividad actividad : actividades) {
            if (actividad.getVoluntarios().contains(voluntario)) {
                actividadesAsignadas.add(actividad);
            }
        }
        return actividadesAsignadas;
    }
}

