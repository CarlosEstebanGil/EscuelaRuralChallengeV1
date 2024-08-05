package com.charly.sbSec3Jwt.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.charly.sbSec3Jwt.entity.Role;
import com.charly.sbSec3Jwt.entity.User;
import com.charly.sbSec3Jwt.escuelaRural.alumno.Alumno;
import com.charly.sbSec3Jwt.escuelaRural.alumno.AlumnoRepository;
import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.dtos.UsuarioDTO;
import com.charly.sbSec3Jwt.escuelaRural.reporte.Reporte;
import com.charly.sbSec3Jwt.escuelaRural.reporte.ReporteDTO;
import com.charly.sbSec3Jwt.escuelaRural.reporte.ReporteMapper;
import com.charly.sbSec3Jwt.escuelaRural.reporte.ReporteRepository;
import com.charly.sbSec3Jwt.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void updateUserRole(String userEmail, Role newRole) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(newRole);
        userRepository.save(user);
    }

    // Métodos casos uso role usuario
    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private ReporteMapper reporteMapper;
    
    // Métodos casos uso role usuario
    
    @Override
    public UsuarioDTO verPerfil(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UsuarioDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().name()
        );
    }

    @Override
    public UsuarioDTO actualizarPerfil(UsuarioDTO usuarioDTO) {
        User user = userRepository.findByEmail(usuarioDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(usuarioDTO.getFirstName());
        user.setLastName(usuarioDTO.getLastName());
        user.setPassword(usuarioDTO.getPassword()); 
        userRepository.save(user);
        return new UsuarioDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().name()
        );
    }
   
}