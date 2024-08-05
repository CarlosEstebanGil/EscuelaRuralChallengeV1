package com.charly.sbSec3Jwt.escuelaRural.asistencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.charly.sbSec3Jwt.escuelaRural.fecha.Fecha;
import com.charly.sbSec3Jwt.escuelaRural.reporte.Reporte;
import com.charly.sbSec3Jwt.escuelaRural.reporte.ReporteService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/asistencias")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;
    
    @Autowired
    private ReporteService reporteService;

    // Métodos para uso de asistencias
    @GetMapping("/presentes/count")
    public ResponseEntity<Long> contarAsistenciasPresentes(@RequestParam LocalDateTime fecha) {
        Fecha fechaEntity = new Fecha(fecha, false, false); 
        long count = asistenciaService.contarAsistenciasPresentes(fechaEntity);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/presentes")
    public ResponseEntity<List<Asistencia>> obtenerAsistenciasPresentes(@RequestParam LocalDateTime fecha) {
        Fecha fechaEntity = new Fecha(fecha, false, false); 
        List<Asistencia> asistencias = asistenciaService.obtenerAsistenciasPresentes(fechaEntity);
        return ResponseEntity.ok(asistencias);
    }

    @GetMapping("/ausentes")
    public ResponseEntity<List<Asistencia>> obtenerAsistenciasAusentes(@RequestParam LocalDateTime fecha) {
        Fecha fechaEntity = new Fecha(fecha, false, false); 
        List<Asistencia> asistencias = asistenciaService.obtenerAsistenciasAusentes(fechaEntity);
        return ResponseEntity.ok(asistencias);
    }

    // Métodos CRUD para asistencias
    @GetMapping
    public List<Asistencia> getAllAsistencias() {
        return asistenciaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asistencia> getAsistenciaById(@PathVariable Long id) {
        Optional<Asistencia> asistencia = asistenciaService.findById(id);
        return asistencia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Asistencia createAsistencia(@RequestBody Asistencia asistencia) {
        return asistenciaService.save(asistencia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asistencia> updateAsistencia(@PathVariable Long id, @RequestBody Asistencia asistenciaDetails) {
        Optional<Asistencia> asistencia = asistenciaService.findById(id);
        if (asistencia.isPresent()) {
            Asistencia updatedAsistencia = asistencia.get();
            updatedAsistencia.setFecha(asistenciaDetails.getFecha());
            updatedAsistencia.setAlumno(asistenciaDetails.getAlumno());
            updatedAsistencia.setPresente(asistenciaDetails.isPresente());
            updatedAsistencia.setJustificacion(asistenciaDetails.getJustificacion());
            return ResponseEntity.ok(asistenciaService.save(updatedAsistencia));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsistencia(@PathVariable Long id) {
        if (asistenciaService.findById(id).isPresent()) {
            asistenciaService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para generar reportes diarios
    @GetMapping("/reporte/dia")
    public ResponseEntity<Reporte> getReporteDiario(@RequestParam("fecha") String fechaStr) {
        LocalDateTime fecha = LocalDateTime.parse(fechaStr);
        long presentes = asistenciaService.getCantAlumnosPresentesPorDia(fecha);
        long ausentes = asistenciaService.getCantAlumnosAusentesPorDia(fecha);

        Reporte reporte = new Reporte();
        Fecha fechaEntity = new Fecha();
        fechaEntity.setFecha(fecha);
        reporte.setFecha(fechaEntity);
        reporte.setCantidadAlumnosDelCurso(presentes + ausentes);
        reporte.setCantidadAlumnosPresentes(presentes);
        reporte.setCantidadAlumnosAusentes(ausentes);
        reporte.setCantidadAlumnosAusentesJustificados(asistenciaService.countJustificadosPorFecha(fecha));
        reporte.setCantidadAlumnosAusentesNoJustificados(ausentes - asistenciaService.countJustificadosPorFecha(fecha));

        // Guardo el reporte en la base de datos
        reporte = reporteService.save(reporte);
        
        return ResponseEntity.ok(reporte);
    }
}
