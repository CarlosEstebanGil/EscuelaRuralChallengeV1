package com.charly.sbSec3Jwt.escuelaRural.miembro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/miembros")
public class MiembroController {

    @Autowired
    private MiembroService miembroService;

    @GetMapping
    public List<Miembro> getAllMiembros() {
        return miembroService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Miembro> getMiembroById(@PathVariable Long id) {
        Optional<Miembro> miembro = miembroService.findById(id);
        return miembro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Miembro createMiembro(@RequestBody Miembro miembro) {
        return miembroService.save(miembro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Miembro> updateMiembro(@PathVariable Long id, @RequestBody Miembro miembroDetails) {
        Optional<Miembro> miembro = miembroService.findById(id);
        if (miembro.isPresent()) {
            Miembro updatedMiembro = miembro.get();
            updatedMiembro.setNombre(miembroDetails.getNombre());
            updatedMiembro.setApellido(miembroDetails.getApellido());
            updatedMiembro.setDni(miembroDetails.getDni());
            updatedMiembro.setPassword(miembroDetails.getPassword());
            updatedMiembro.setNacionalidad(miembroDetails.getNacionalidad());
            updatedMiembro.setTipoUsuario(miembroDetails.getTipoUsuario());
            updatedMiembro.setEmail(miembroDetails.getEmail());
            return ResponseEntity.ok(miembroService.save(updatedMiembro));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMiembro(@PathVariable Long id) {
        if (miembroService.findById(id).isPresent()) {
            miembroService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}