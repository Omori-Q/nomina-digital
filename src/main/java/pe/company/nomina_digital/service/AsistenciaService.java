package pe.company.nomina_digital.service;

import org.springframework.stereotype.Service;
import pe.company.nomina_digital.model.Asistencia;
import pe.company.nomina_digital.model.Personal;
import pe.company.nomina_digital.repository.AsistenciaRepository;
import pe.company.nomina_digital.repository.PersonalRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class AsistenciaService {
    private final AsistenciaRepository asistenciaRepository;
    private final PersonalRepository personalRepository;

    public AsistenciaService(AsistenciaRepository asistenciaRepository, PersonalRepository personalRepository) {
        this.asistenciaRepository = asistenciaRepository;
        this.personalRepository = personalRepository;
    }

    public Asistencia marcarEntrada(Integer idPersonal) {
        Optional<Personal> empleado = personalRepository.findById(idPersonal);
        if (empleado.isEmpty()) {
            throw new RuntimeException("Empleado no encontrado");
        }

        Optional<Asistencia> existente = asistenciaRepository.findByPersonalIdPersonalAndFecha(idPersonal, LocalDate.now());
        if (existente.isPresent()) {
            throw new RuntimeException("Ya existe un registro de entrada para hoy");
        }

        Asistencia asistencia = new Asistencia();
        asistencia.setPersonal(empleado.get());
        asistencia.setFecha(LocalDate.now());
        asistencia.setHoraEntrada(LocalTime.now());

        return asistenciaRepository.save(asistencia);
    }

    public Asistencia marcarSalida(Integer idPersonal) {
        Optional<Asistencia> asistenciaOpt = asistenciaRepository.findByPersonalIdPersonalAndFecha(idPersonal, LocalDate.now());
        if (asistenciaOpt.isEmpty()) {
            throw new RuntimeException("El empleado no tiene entrada registrada hoy");
        }

        Asistencia asistencia = asistenciaOpt.get();
        if (asistencia.getHoraSalida() != null) {
            throw new RuntimeException("Ya se registró la salida para hoy");
        }

        asistencia.setHoraSalida(LocalTime.now());

        Duration duracion = Duration.between(asistencia.getHoraEntrada(), asistencia.getHoraSalida());
        String horas = String.format("%02d:%02d", duracion.toHours(), duracion.toMinutesPart());
        asistencia.setHorasTrabajadas(horas);

        return asistenciaRepository.save(asistencia);
    }
}
