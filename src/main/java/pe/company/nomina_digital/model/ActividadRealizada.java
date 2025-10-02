package pe.company.nomina_digital.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "actividad_realizada")
public class ActividadRealizada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idActividadRealizada;

    @ManyToOne
    @JoinColumn(name = "id_actividad", nullable = false)
    private Actividad actividad;

    @ManyToOne
    @JoinColumn(name = "id_asistencia", nullable = false)
    private Asistencia asistencia;

    private LocalDate fecha;
    private Double horasDedicadas;

    public Integer getIdActividadRealizada() {
        return idActividadRealizada;
    }

    public void setIdActividadRealizada(Integer idActividadRealizada) {
        this.idActividadRealizada = idActividadRealizada;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Asistencia getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Asistencia asistencia) {
        this.asistencia = asistencia;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getHorasDedicadas() {
        return horasDedicadas;
    }

    public void setHorasDedicadas(Double horasDedicadas) {
        this.horasDedicadas = horasDedicadas;
    }
}
