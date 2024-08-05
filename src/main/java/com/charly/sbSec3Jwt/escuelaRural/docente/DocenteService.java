package com.charly.sbSec3Jwt.escuelaRural.docente;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.charly.sbSec3Jwt.escuelaRural.alumno.Alumno;
import com.charly.sbSec3Jwt.escuelaRural.asistencia.Asistencia;
import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.dtos.CalificacionDTO;
import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.dtos.TareaDTO;
import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.entities.Calificacion;
import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.entities.Tarea;
import com.charly.sbSec3Jwt.escuelaRural.fecha.Fecha;
import com.charly.sbSec3Jwt.escuelaRural.justificacion.Justificacion;
import com.charly.sbSec3Jwt.escuelaRural.reporte.Reporte;

public interface DocenteService {

    Asistencia tomarLista(Long alumnoId, boolean presente, Fecha fecha, Justificacion justificacion);
    Reporte generarReporte(Fecha fecha);
    
    public List<Alumno> verAlumnos();

    public TareaDTO crearTarea(TareaDTO tareaDTO);
    
    public CalificacionDTO calificarTarea(CalificacionDTO calificacionDTO);
        
	List<Docente> findAll();

	Optional<Docente> findById(Long id);

	Docente save(Docente docente);

	void deleteById(Long id);

}