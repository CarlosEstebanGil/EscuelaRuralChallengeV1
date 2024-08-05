package com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.controllers;

import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.dtos.UsuarioDTO;
import com.charly.sbSec3Jwt.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/user")
public class RoleUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/ver-perfil")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> verPerfil(@RequestParam String email) {
        return ResponseEntity.ok(userService.verPerfil(email));
    }

    @PutMapping("/actualizar-perfil")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> actualizarPerfil(@RequestBody UsuarioDTO usuario) {
        return ResponseEntity.ok(userService.actualizarPerfil(usuario));
    }
}