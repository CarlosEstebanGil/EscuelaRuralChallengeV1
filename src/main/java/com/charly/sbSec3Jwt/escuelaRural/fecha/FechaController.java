package com.charly.sbSec3Jwt.escuelaRural.fecha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fechas")
public class FechaController {

    @Autowired
    private FechaService fechaService;

   /* @GetMapping
    public List<Fecha> getAllFechas() {
        return fechaService.findAll();
    }*/
    @GetMapping
    public List<FechaDTO> getAllFechas() {
        return fechaService.findAll();
    }

    @GetMapping("/{fecha}")
    public ResponseEntity<Fecha> getFechaById(@PathVariable String fecha) {
        LocalDateTime fechaDateTime = LocalDateTime.parse(fecha);
        Optional<Fecha> fechaOptional = fechaService.findById(fechaDateTime);
        return fechaOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Fecha createFecha(@RequestBody Fecha fecha) {
        return fechaService.save(fecha);
    }

    @PutMapping("/{fecha}")
    public ResponseEntity<Fecha> updateFecha(@PathVariable String fecha, @RequestBody Fecha fechaDetails) {
        LocalDateTime fechaDateTime = LocalDateTime.parse(fecha);
        Optional<Fecha> fechaOptional = fechaService.findById(fechaDateTime);
        if (fechaOptional.isPresent()) {
            Fecha updatedFecha = fechaOptional.get();
            updatedFecha.setLluvioso(fechaDetails.isLluvioso());
            updatedFecha.setFeriado(fechaDetails.isFeriado());
            return ResponseEntity.ok(fechaService.save(updatedFecha));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{fecha}")
    public ResponseEntity<Void> deleteFecha(@PathVariable String fecha) {
        LocalDateTime fechaDateTime = LocalDateTime.parse(fecha);
        if (fechaService.findById(fechaDateTime).isPresent()) {
            fechaService.deleteById(fechaDateTime);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
