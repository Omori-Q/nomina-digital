package pe.company.nomina_digital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.company.nomina_digital.model.Actividad;

public interface ActividadRepository extends JpaRepository<Actividad, Integer> {
}
