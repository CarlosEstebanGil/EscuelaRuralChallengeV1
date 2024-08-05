package com.charly.sbSec3Jwt.escuelaRural.docente;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charly.sbSec3Jwt.escuelaRural.alumno.Alumno;
import com.charly.sbSec3Jwt.escuelaRural.alumno.AlumnoRepository;
import com.charly.sbSec3Jwt.escuelaRural.asistencia.Asistencia;
import com.charly.sbSec3Jwt.escuelaRural.asistencia.AsistenciaRepository;
import com.charly.sbSec3Jwt.escuelaRural.asistencia.AsistenciaService;
import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.dtos.CalificacionDTO;
import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.dtos.TareaDTO;
import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.entities.Calificacion;
import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.entities.Tarea;
import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.repositories.CalificacionRepository;
import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.repositories.TareaRepository;
import com.charly.sbSec3Jwt.escuelaRural.fecha.Fecha;
import com.charly.sbSec3Jwt.escuelaRural.fecha.FechaRepository;
import com.charly.sbSec3Jwt.escuelaRural.justificacion.Justificacion;
import com.charly.sbSec3Jwt.escuelaRural.reporte.Reporte;
import com.charly.sbSec3Jwt.escuelaRural.reporte.ReporteRepository;

@Service
public class DocenteServiceImpl implements DocenteService {
	
	@Autowired
	private DocenteRepository docenteRepository;

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;
    
    @Autowired
    private AsistenciaService asistenciaService;

    @Autowired
    private FechaRepository fechaRepository;
    
    @Autowired
    private ReporteRepository reporteRepository;
    
    @Autowired
    private AsistenciaRepository asistenciaRepository;

	 //metodos caso uso role Docente:

    @Override
    public Asistencia tomarLista(Long alumnoId, boolean presente, Fecha fecha, Justificacion justificacion) {
        Alumno alumno = alumnoRepository.findById(alumnoId)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        fechaRepository.save(fecha);

        Asistencia asistencia = new Asistencia(null, alumno, fecha, presente, justificacion);
        return asistenciaRepository.save(asistencia);
    }

    @Override
    public Reporte generarReporte(Fecha fecha) {
        List<Asistencia> asistencias = asistenciaRepository.findByFecha(fecha);

        long totalAlumnos = asistencias.size();
        long presentes = asistencias.stream().filter(Asistencia::isPresente).count();
        long ausentes = totalAlumnos - presentes;
        long justificados = asistencias.stream().filter(a -> !a.isPresente() && a.getJustificacion() != null).count();
        long noJustificados = ausentes - justificados;

        Reporte reporte = new Reporte(null, fecha.getFecha(), fecha, totalAlumnos, presentes, ausentes, justificados, noJustificados);
        return reporteRepository.save(reporte);
    }
    //otros :
    @Override
    public List<Alumno> verAlumnos() {
        return alumnoRepository.findAll();
    }

    @Override
    public TareaDTO crearTarea(TareaDTO tareaDTO) {
        Tarea tarea = new Tarea(tareaDTO.getTitulo(), tareaDTO.getDescripcion(), tareaDTO.getFechaEntrega());
        tarea = tareaRepository.save(tarea);
        return new TareaDTO(tarea.getId(), tarea.getTitulo(), tarea.getDescripcion(), tarea.getFechaEntrega());
    }
    
    @Override
    public CalificacionDTO calificarTarea(CalificacionDTO calificacionDTO) {
        Calificacion calificacion = new Calificacion(calificacionDTO.getTareaId(), calificacionDTO.getAlumnoId(), calificacionDTO.getNota());
        calificacion = calificacionRepository.save(calificacion);
        return new CalificacionDTO(calificacion.getId(), calificacion.getTareaId(), calificacion.getAlumnoId(), calificacion.getNota());
    }
    
	
	//metodos crud entidad Docente:
	@Override
	public List<Docente> findAll() {
	    return docenteRepository.findAll();
	}

	@Override
	public Optional<Docente> findById(Long id) {
		return docenteRepository.findById(id);
	}

	@Override
	public Docente save(Docente docente) {
		return docenteRepository.save(docente);
	}

	@Override
	public void deleteById(Long id) {
		docenteRepository.deleteById(id);
	}
	
}
