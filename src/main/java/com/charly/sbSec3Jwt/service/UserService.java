package com.charly.sbSec3Jwt.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.charly.sbSec3Jwt.entity.Role;
import com.charly.sbSec3Jwt.entity.User;
import com.charly.sbSec3Jwt.escuelaRural.alumno.Alumno;
import com.charly.sbSec3Jwt.escuelaRural.casosUsoPorRole.dtos.UsuarioDTO;
import com.charly.sbSec3Jwt.escuelaRural.reporte.ReporteDTO;

public interface UserService {

	void updateUserRole(String userEmail, Role newRole);


	 public UsuarioDTO verPerfil(String email);

	 public UsuarioDTO actualizarPerfil(UsuarioDTO usuarioDTO);
}