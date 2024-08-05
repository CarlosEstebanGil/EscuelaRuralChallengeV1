package com.charly.sbSec3Jwt.escuelaRural.asistencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charly.sbSec3Jwt.escuelaRural.alumno.Alumno;
import com.charly.sbSec3Jwt.escuelaRural.alumno.AlumnoRepository;
import com.charly.sbSec3Jwt.escuelaRural.fecha.Fecha;
import com.charly.sbSec3Jwt.escuelaRural.fecha.FechaRepository;
import com.charly.sbSec3Jwt.escuelaRural.justificacion.Justificacion;
import com.charly.sbSec3Jwt.escuelaRural.justificacion.JustificacionRepository;
import com.charly.sbSec3Jwt.escuelaRural.motivo.Motivo;
import com.charly.sbSec3Jwt.escuelaRural.motivo.MotivoRepository;
import com.charly.sbSec3Jwt.escuelaRural.reporte.Reporte;
import com.charly.sbSec3Jwt.escuelaRural.reporte.ReporteRepository;
import com.charly.sbSec3Jwt.escuelaRural.utils.WeatherService;
import com.charly.sbSec3Jwt.escuelaRural.utils.WeatherServiceMock;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
//import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//@Log4j2
@Service
public class AsistenciaServiceImpl implements AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    private FechaRepository fechaRepository;

    //c.uso methods
    @Autowired
    private AlumnoRepository alumnoRepository;
    
    @Autowired
    private JustificacionRepository justificacionRepository;
    
    @Autowired
    private MotivoRepository motivoRepository;

    @Autowired
    private ReporteRepository reporteRepository;
    
    @Autowired
    private WeatherServiceMock weatherServiceMock;
    
    @Autowired
    private WeatherService weatherService;
    
    @Override
    public Asistencia createAsistencia(Long alumnoId, boolean presente, boolean lluvioso,LocalDateTime fecha, String motivoDescripcion) {
        Alumno alumno = alumnoRepository.findById(alumnoId)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        Fecha fechaEntity; 
        
        fechaEntity = new Fecha(fecha, lluvioso,presente);

        Justificacion justificacion = null;
        if (!presente) {
            Motivo motivo = motivoRepository.findByDescripcion(motivoDescripcion)
                    .orElseGet(() -> motivoRepository.save(new Motivo(null, motivoDescripcion)));
            justificacion = justificacionRepository.save(new Justificacion(motivo, "Justificación de ausencia"));
        }

        Asistencia asistencia = new Asistencia(null, alumno, fechaEntity, presente, justificacion);
        return asistenciaRepository.save(asistencia);
    }
    
    @Override
    public Reporte generarReporte(LocalDateTime fecha) {
        Fecha fechaEntity = fechaRepository.findById(fecha)
                .orElseThrow(() -> new RuntimeException("Fecha no encontrada"));

        List<Asistencia> asistencias = asistenciaRepository.findByFecha(fechaEntity);

        long totalAlumnos = asistencias.size();
        long presentes = asistencias.stream().filter(Asistencia::isPresente).count();
        long ausentes = totalAlumnos - presentes;
        long justificados = asistencias.stream().filter(a -> !a.isPresente() && a.getJustificacion() != null).count();
        long noJustificados = ausentes - justificados;

        Reporte reporte = new Reporte(null, fechaEntity.getFecha(), fechaEntity, totalAlumnos, presentes, ausentes, justificados, noJustificados);
        return reporteRepository.save(reporte);
    }
    
    @Override
    public long contarAsistenciasPresentes(Fecha fecha) {
        return asistenciaRepository.findByFechaAndPresente(fecha, true).size();
    }
    @Override
    public List<Asistencia> obtenerAsistenciasPresentes(Fecha fecha) {
        return asistenciaRepository.findByFechaAndPresente(fecha, true);
    }
    @Override
    public List<Asistencia> obtenerAsistenciasAusentes(Fecha fecha) {
        return asistenciaRepository.findByFechaAndPresenteFalse(fecha);
    }
    
    // crud methods asistencia:
    @Override
    public List<Asistencia> findAll() {
        return asistenciaRepository.findAll();
    }

    @Override
    public Optional<Asistencia> findById(Long id) {
        return asistenciaRepository.findById(id);
    }

    @Override
    public Asistencia save(Asistencia asistencia) {
        return asistenciaRepository.save(asistencia);
    }

    @Override
    public void deleteById(Long id) {
        asistenciaRepository.deleteById(id);
    }

    @Override
    public List<Asistencia> findByFechaAndPresente(LocalDateTime fecha, boolean presente) {
        Fecha fechaEntity = fechaRepository.findById(fecha).orElseThrow(() -> new RuntimeException("Fecha no encontrada"));
        return asistenciaRepository.findByFechaAndPresente(fechaEntity, presente);
    }

    @Override
    public long getCantAlumnosPresentesPorDia(LocalDateTime fecha) {
        Fecha fechaEntity = fechaRepository.findById(fecha).orElseThrow(() -> new RuntimeException("Fecha no encontrada"));
        return asistenciaRepository.countByFechaAndPresente(fechaEntity, true);
    }

    @Override
    public long getCantAlumnosAusentesPorDia(LocalDateTime fecha) {
        Fecha fechaEntity = fechaRepository.findById(fecha).orElseThrow(() -> new RuntimeException("Fecha no encontrada"));
        return asistenciaRepository.countByFechaAndPresente(fechaEntity, false);
    }

    @Override
    public long countJustificadosPorFecha(LocalDateTime fecha) {
        Fecha fechaEntity = fechaRepository.findById(fecha).orElseThrow(() -> new RuntimeException("Fecha no encontrada"));
        return asistenciaRepository.countByFechaAndPresenteAndJustificacionIsNotNull(fechaEntity, false);
    }
}
