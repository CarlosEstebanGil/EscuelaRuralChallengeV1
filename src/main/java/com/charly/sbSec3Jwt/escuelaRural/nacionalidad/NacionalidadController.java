package com.charly.sbSec3Jwt.escuelaRural.nacionalidad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nacionalidades")
public class NacionalidadController {

    @Autowired
    private NacionalidadService nacionalidadService;

    @GetMapping
    public List<Nacionalidad> getAllNacionalidades() {
        return nacionalidadService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nacionalidad> getNacionalidadById(@PathVariable Long id) {
        Optional<Nacionalidad> nacionalidad = nacionalidadService.findById(id);
        return nacionalidad.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Nacionalidad createNacionalidad(@RequestBody Nacionalidad nacionalidad) {
        return nacionalidadService.save(nacionalidad);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nacionalidad> updateNacionalidad(@PathVariable Long id, @RequestBody Nacionalidad nacionalidadDetails) {
        Optional<Nacionalidad> nacionalidad = nacionalidadService.findById(id);
        if (nacionalidad.isPresent()) {
            Nacionalidad updatedNacionalidad = nacionalidad.get();
            updatedNacionalidad.setNombre(nacionalidadDetails.getNombre());
            return ResponseEntity.ok(nacionalidadService.save(updatedNacionalidad));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNacionalidad(@PathVariable Long id) {
        if (nacionalidadService.findById(id).isPresent()) {
            nacionalidadService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}