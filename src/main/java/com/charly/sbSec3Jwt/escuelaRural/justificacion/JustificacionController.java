package com.charly.sbSec3Jwt.escuelaRural.justificacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/justificaciones")
public class JustificacionController {

    @Autowired
    private JustificacionService justificacionService;

    @GetMapping
    public List<Justificacion> getAllJustificaciones() {
        return justificacionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Justificacion> getJustificacionById(@PathVariable Long id) {
        Optional<Justificacion> justificacion = justificacionService.findById(id);
        return justificacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Justificacion createJustificacion(@RequestBody Justificacion justificacion) {
        return justificacionService.save(justificacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Justificacion> updateJustificacion(@PathVariable Long id, @RequestBody Justificacion justificacionDetails) {
        Optional<Justificacion> justificacion = justificacionService.findById(id);
        if (justificacion.isPresent()) {
            Justificacion updatedJustificacion = justificacion.get();
            updatedJustificacion.setMotivo(justificacionDetails.getMotivo());
            updatedJustificacion.setDescripcion(justificacionDetails.getDescripcion());
            return ResponseEntity.ok(justificacionService.save(updatedJustificacion));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJustificacion(@PathVariable Long id) {
        if (justificacionService.findById(id).isPresent()) {
            justificacionService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
