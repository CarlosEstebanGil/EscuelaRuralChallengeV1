package com.charly.sbSec3Jwt.escuelaRural.preceptor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.charly.sbSec3Jwt.escuelaRural.alumno.Alumno;
import com.charly.sbSec3Jwt.escuelaRural.asistencia.Asistencia;
import com.charly.sbSec3Jwt.escuelaRural.fecha.Fecha;
import com.charly.sbSec3Jwt.escuelaRural.justificacion.Justificacion;
import com.charly.sbSec3Jwt.escuelaRural.reporte.Reporte;
import com.charly.sbSec3Jwt.escuelaRural.reporte.ReporteDTO;

public interface PreceptorService {


	Asistencia tomarLista(Long alumnoId, boolean presente, boolean lluvioso, Fecha fecha, Justificacion justificacion);
    Reporte generarReporte(Fecha fecha);

    List<Asistencia> obtenerAsistenciasPorFechaYEstado(Fecha fecha, boolean presente);
    	
    
	//otros adicionales :
	public List<Alumno> verTodosLosAlumnos();
	    
	ReporteDTO crearReporte(ReporteDTO reporteDTO);

	List<ReporteDTO> verReportes();

	
	
	// Métodos CRUD para la entidad Preceptor
	List<Preceptor> findAll();

	Optional<Preceptor> findById(Long id);

	Preceptor save(Preceptor preceptor);

	void deleteById(Long id);

}