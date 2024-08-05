package com.charly.sbSec3Jwt.escuelaRural.preceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/preceptores")
public class PreceptorController {

    @Autowired
    private PreceptorService preceptorService;

    @GetMapping
    public List<Preceptor> getAllPreceptores() {
        return preceptorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Preceptor> getPreceptorById(@PathVariable Long id) {
        Optional<Preceptor> preceptor = preceptorService.findById(id);
        return preceptor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Preceptor createPreceptor(@RequestBody Preceptor preceptor) {
        return preceptorService.save(preceptor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Preceptor> updatePreceptor(@PathVariable Long id, @RequestBody Preceptor preceptorDetails) {
        Optional<Preceptor> preceptor = preceptorService.findById(id);
        if (preceptor.isPresent()) {
            Preceptor updatedPreceptor = preceptor.get();
            updatedPreceptor.setMiembro(preceptorDetails.getMiembro());
            updatedPreceptor.setCurso(preceptorDetails.getCurso());
            return ResponseEntity.ok(preceptorService.save(updatedPreceptor));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePreceptor(@PathVariable Long id) {
        if (preceptorService.findById(id).isPresent()) {
            preceptorService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}