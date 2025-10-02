package pe.company.nomina_digital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.company.nomina_digital.model.Asistencia;
import pe.company.nomina_digital.service.AsistenciaService;

@Controller
@RequestMapping("/api/asistencia")
public class AsistenciaController {
    private final AsistenciaService asistenciaService;

    public AsistenciaController(AsistenciaService asistenciaService) {
        this.asistenciaService = asistenciaService;
    }

    @PostMapping("/entrada/{idPersonal}")
    public Asistencia marcarEntrada(@PathVariable Integer idPersonal) {
        return asistenciaService.marcarEntrada(idPersonal);
    }

    @PostMapping("/salida/{idPersonal}")
    public Asistencia marcarSalida(@PathVariable Integer idPersonal) {
        return asistenciaService.marcarSalida(idPersonal);
    }
}
