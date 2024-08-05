package com.charly.sbSec3Jwt.escuelaRural.preceptor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charly.sbSec3Jwt.escuelaRural.alumno.Alumno;
import com.charly.sbSec3Jwt.escuelaRural.alumno.AlumnoRepository;
import com.charly.sbSec3Jwt.escuelaRural.asistencia.Asistencia;
import com.charly.sbSec3Jwt.escuelaRural.asistencia.AsistenciaRepository;
import com.charly.sbSec3Jwt.escuelaRural.asistencia.AsistenciaService;
import com.charly.sbSec3Jwt.escuelaRural.fecha.Fecha;
import com.charly.sbSec3Jwt.escuelaRural.fecha.FechaRepository;
import com.charly.sbSec3Jwt.escuelaRural.justificacion.Justificacion;
import com.charly.sbSec3Jwt.escuelaRural.reporte.Reporte;
import com.charly.sbSec3Jwt.escuelaRural.reporte.ReporteDTO;
import com.charly.sbSec3Jwt.escuelaRural.reporte.ReporteMapper;
import com.charly.sbSec3Jwt.escuelaRural.reporte.ReporteRepository;

@Service
public class PreceptorServiceImpl implements PreceptorService {

    @Autowired
    private PreceptorRepository preceptorRepository;

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private FechaRepository fechaRepository;

    @Autowired
    private ReporteMapper reporteMapper;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private AsistenciaService asistenciaService;

    @Autowired
    AsistenciaRepository asistenciaRepository;

    // Métodos de casos de uso para la entidad Preceptor
    
    @Override
    public Asistencia tomarLista(Long alumnoId, boolean presente, boolean lluvioso, Fecha fecha, Justificacion justificacion) {
        Alumno alumno = alumnoRepository.findById(alumnoId)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        fechaRepository.save(fecha);

        String strMotivo = null;
        if (justificacion!=null) strMotivo = justificacion.getMotivo().getDescripcion();
        
        /*Asistencia asistencia = asistenciaService.createAsistencia(	alumnoId, presente, fecha.getFecha(), 
        															strMotivo
        															);*/
        Asistencia asistencia = asistenciaService.createAsistencia(alumnoId, presente, lluvioso,fecha.getFecha(), strMotivo);
        
        //Asistencia asistencia = new Asistencia(null, alumno, fecha, presente, justificacion);
        //return asistenciaRepository.save(asistencia);
        return asistencia;
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

    @Override
    public List<Asistencia> obtenerAsistenciasPorFechaYEstado(Fecha fecha, boolean presente) {
        return asistenciaRepository.findByFechaAndPresente(fecha, presente);
    }

    // Otros métodos:
    @Override
    public List<Alumno> verTodosLosAlumnos() {
        return alumnoRepository.findAll();
    }

    @Override
    public ReporteDTO crearReporte(ReporteDTO reporteDTO) {
        Fecha fecha = fechaRepository.findById(reporteDTO.getFechaEntity().getFecha())
                .orElseThrow(() -> new RuntimeException("Fecha no encontrada"));

        Reporte reporte = reporteMapper.toEntity(reporteDTO);
        reporte.setFechaEntity(fecha);

        reporte = reporteRepository.save(reporte);

        return reporteMapper.toDTO(reporte);
    }

    @Override
    public List<ReporteDTO> verReportes() {
        return reporteRepository.findAll().stream()
                .map(reporteMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Métodos CRUD para la entidad Preceptor
    @Override
    public List<Preceptor> findAll() {
        return preceptorRepository.findAll();
    }

    @Override
    public Optional<Preceptor> findById(Long id) {
        return preceptorRepository.findById(id);
    }

    @Override
    public Preceptor save(Preceptor preceptor) {
        return preceptorRepository.save(preceptor);
    }

    @Override
    public void deleteById(Long id) {
        preceptorRepository.deleteById(id);
    }
}
