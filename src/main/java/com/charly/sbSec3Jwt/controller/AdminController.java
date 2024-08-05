package com.charly.sbSec3Jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.charly.sbSec3Jwt.entity.Role;
import com.charly.sbSec3Jwt.service.UserServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserServiceImpl userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/{username}/role")
    public ResponseEntity<?> updateUserRole(@PathVariable String username, @RequestBody Role newRole) {
        userService.updateUserRole(username, newRole);
        return ResponseEntity.ok().build();
    }
    
    //Este controlador solo estará accesible para los usuarios con el rol ADMIN.
    @GetMapping("/dashboard")
    public ResponseEntity<String> adminDashboard() {
        return ResponseEntity.ok("Prueba acceso ADMIN");
    }
}