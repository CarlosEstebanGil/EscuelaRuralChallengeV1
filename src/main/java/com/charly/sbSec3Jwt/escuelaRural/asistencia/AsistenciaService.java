package com.charly.sbSec3Jwt.escuelaRural.asistencia;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.charly.sbSec3Jwt.escuelaRural.alumno.Alumno;
import com.charly.sbSec3Jwt.escuelaRural.fecha.Fecha;
import com.charly.sbSec3Jwt.escuelaRural.justificacion.Justificacion;
import com.charly.sbSec3Jwt.escuelaRural.motivo.Motivo;
import com.charly.sbSec3Jwt.escuelaRural.reporte.Reporte;

public interface AsistenciaService {


	public Asistencia createAsistencia(Long alumnoId, boolean presente, boolean lluvioso,LocalDateTime fecha, String motivoDescripcion);
	
    public Reporte generarReporte(LocalDateTime fecha);

    public long contarAsistenciasPresentes(Fecha fecha); 
    public List<Asistencia> obtenerAsistenciasPresentes(Fecha fecha); 
    public List<Asistencia> obtenerAsistenciasAusentes(Fecha fecha);

	
	List<Asistencia> findAll();

	Optional<Asistencia> findById(Long id);

	Asistencia save(Asistencia asistencia);

	void deleteById(Long id);

	List<Asistencia> findByFechaAndPresente(LocalDateTime fecha, boolean presente);

	long getCantAlumnosPresentesPorDia(LocalDateTime fecha);

	long getCantAlumnosAusentesPorDia(LocalDateTime fecha);

	long countJustificadosPorFecha(LocalDateTime fecha);

}