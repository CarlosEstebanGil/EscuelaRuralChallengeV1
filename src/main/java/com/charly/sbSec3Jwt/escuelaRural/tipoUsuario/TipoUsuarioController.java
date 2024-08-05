package com.charly.sbSec3Jwt.escuelaRural.tipoUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipos-usuario")
public class TipoUsuarioController {

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @GetMapping
    public List<TipoUsuario> getAllTiposUsuario() {
        return tipoUsuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuario> getTipoUsuarioById(@PathVariable Long id) {
        Optional<TipoUsuario> tipoUsuario = tipoUsuarioService.findById(id);
        return tipoUsuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TipoUsuario createTipoUsuario(@RequestBody TipoUsuario tipoUsuario) {
        return tipoUsuarioService.save(tipoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoUsuario> updateTipoUsuario(@PathVariable Long id, @RequestBody TipoUsuario tipoUsuarioDetails) {
        Optional<TipoUsuario> tipoUsuario = tipoUsuarioService.findById(id);
        if (tipoUsuario.isPresent()) {
            TipoUsuario updatedTipoUsuario = tipoUsuario.get();
            updatedTipoUsuario.setNombre(tipoUsuarioDetails.getNombre());
            return ResponseEntity.ok(tipoUsuarioService.save(updatedTipoUsuario));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoUsuario(@PathVariable Long id) {
        if (tipoUsuarioService.findById(id).isPresent()) {
            tipoUsuarioService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}