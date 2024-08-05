package com.charly.sbSec3Jwt.escuelaRural.motivo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/motivos")
public class MotivoController {

    @Autowired
    private MotivoService motivoService;

    @GetMapping
    public List<Motivo> getAllMotivos() {
        return motivoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Motivo> getMotivoById(@PathVariable Long id) {
        Optional<Motivo> motivo = motivoService.findById(id);
        return motivo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Motivo createMotivo(@RequestBody Motivo motivo) {
        return motivoService.save(motivo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Motivo> updateMotivo(@PathVariable Long id, @RequestBody Motivo motivoDetails) {
        Optional<Motivo> motivo = motivoService.findById(id);
        if (motivo.isPresent()) {
            Motivo updatedMotivo = motivo.get();
            updatedMotivo.setDescripcion(motivoDetails.getDescripcion());
            return ResponseEntity.ok(motivoService.save(updatedMotivo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMotivo(@PathVariable Long id) {
        if (motivoService.findById(id).isPresent()) {
            motivoService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
