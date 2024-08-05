package com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.charly.sbSec3Jwt.escuelaRural.asistencia.Asistencia;
import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.dtos.CalificacionDTO;
import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.dtos.TareaDTO;
import com.charly.sbSec3Jwt.escuelaRural.docente.DocenteService;
import com.charly.sbSec3Jwt.escuelaRural.fecha.Fecha;
import com.charly.sbSec3Jwt.escuelaRural.justificacion.Justificacion;
import com.charly.sbSec3Jwt.escuelaRural.reporte.Reporte;

import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/docente")
public class RoleDocenteController {

    @Autowired
    private DocenteService docenteService;

    @PostMapping("/tomar-lista")
    @PreAuthorize("hasRole('DOCENTE')")
    public Asistencia tomarLista(@RequestParam Long alumnoId, @RequestParam boolean presente, @RequestBody Fecha fecha, @RequestBody(required = false) Justificacion justificacion) {
        return docenteService.tomarLista(alumnoId, presente, fecha, justificacion);
    }

    @GetMapping("/reporte")
    @PreAuthorize("hasRole('DOCENTE')")
    public Reporte generarReporte(@RequestBody Fecha fecha) {
        return docenteService.generarReporte(fecha);
    }
    
    @GetMapping("/ver-alumnos")
    @PreAuthorize("hasRole('DOCENTE')")
    public ResponseEntity<?> verAlumnos() {
        return ResponseEntity.ok(docenteService.verAlumnos());
    }

    @PostMapping("/crear-tarea")
    @PreAuthorize("hasRole('DOCENTE')")
    public ResponseEntity<?> crearTarea(@RequestBody TareaDTO tarea) {
        return ResponseEntity.ok(docenteService.crearTarea(tarea));
    }
    
    @PutMapping("/calificar-tarea")
    @PreAuthorize("hasRole('DOCENTE')")
    public ResponseEntity<?> calificarTarea(@RequestBody CalificacionDTO calificacion) {
        return ResponseEntity.ok(docenteService.calificarTarea(calificacion));
    }
}