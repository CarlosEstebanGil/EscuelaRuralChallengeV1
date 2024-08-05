package com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.charly.sbSec3Jwt.escuelaRural.asistencia.Asistencia;
import com.charly.sbSec3Jwt.escuelaRural.fecha.Fecha;
import com.charly.sbSec3Jwt.escuelaRural.justificacion.Justificacion;
import com.charly.sbSec3Jwt.escuelaRural.preceptor.PreceptorService;
import com.charly.sbSec3Jwt.escuelaRural.reporte.Reporte;
import com.charly.sbSec3Jwt.escuelaRural.reporte.ReporteDTO;

import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/preceptor")
public class RolePreceptorController {

    @Autowired
    private PreceptorService preceptorService;

    @PostMapping("/tomar-lista")
    @PreAuthorize("hasRole('PRECEPTOR')")
    public Asistencia tomarLista(@RequestParam Long alumnoId, @RequestParam boolean presente,@RequestParam boolean lluvioso, @RequestBody Fecha fecha, @RequestBody(required = false) Justificacion justificacion) {
        return preceptorService.tomarLista(alumnoId, presente, lluvioso, fecha, justificacion);
    }

    @GetMapping("/reporte")
    @PreAuthorize("hasRole('PRECEPTOR')")
    public Reporte generarReporte(@RequestBody Fecha fecha) {
        return preceptorService.generarReporte(fecha);
    }
    
    @GetMapping("/ver-alumnos")
    @PreAuthorize("hasRole('PRECEPTOR')")
    public ResponseEntity<?> verTodosAlumnos() {
        return ResponseEntity.ok(preceptorService.verTodosLosAlumnos());
    }

    @PostMapping("/crear-reporte")
    @PreAuthorize("hasRole('PRECEPTOR')")
    public ResponseEntity<?> crearReporte(@RequestBody ReporteDTO reporte) {
        return ResponseEntity.ok(preceptorService.crearReporte(reporte));
    }
    
    @GetMapping("/ver-reportes")
    @PreAuthorize("hasRole('PRECEPTOR')")
    public ResponseEntity<?> verReportes() {
        return ResponseEntity.ok(preceptorService.verReportes());
    }
}