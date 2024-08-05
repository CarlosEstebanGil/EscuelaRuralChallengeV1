package com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.charly.sbSec3Jwt.escuelaRural.alumno.AlumnoService;
import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.dtos.TareaDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/alumno")
public class RoleAlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/ver-tareas")
    @PreAuthorize("hasRole('ALUMNO')")
    public ResponseEntity<?> verTareas() {
        return ResponseEntity.ok(alumnoService.verTareas());
    }

    @PostMapping("/enviar-tarea")
    @PreAuthorize("hasRole('ALUMNO')")
    public ResponseEntity<?> enviarTarea(@RequestBody TareaDTO tarea) {
        return ResponseEntity.ok(alumnoService.enviarTarea(tarea));
    }
    
    @GetMapping("/ver-notas")
    @PreAuthorize("hasRole('ALUMNO')")
    public ResponseEntity<?> verNotas() {
        return ResponseEntity.ok(alumnoService.verNotas());
    }
}