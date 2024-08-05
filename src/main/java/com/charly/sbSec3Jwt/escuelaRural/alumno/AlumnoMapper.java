package com.charly.sbSec3Jwt.escuelaRural.alumno;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.charly.sbSec3Jwt.escuelaRural.asistencia.Asistencia;
import com.charly.sbSec3Jwt.escuelaRural.asistencia.AsistenciaDTO;
import com.charly.sbSec3Jwt.escuelaRural.curso.Curso;
import com.charly.sbSec3Jwt.escuelaRural.curso.CursoDTO;
import com.charly.sbSec3Jwt.escuelaRural.miembro.Miembro;
import com.charly.sbSec3Jwt.escuelaRural.miembro.MiembroDTO;

@Component
public class AlumnoMapper {

	 public AlumnoDTO toAlumnoDTO(Alumno alumno) {
	        AlumnoDTO dto = new AlumnoDTO();
	        dto.setId(alumno.getId());
	        dto.setMiembro(toMiembroDTO(alumno.getMiembro()));
	        dto.setCurso(toCursoDTO(alumno.getCurso()));
	        dto.setAsistencias(alumno.getAsistencias().stream().map(this::toAsistenciaDTO).collect(Collectors.toList()));
	        return dto;
	    }

	    private MiembroDTO toMiembroDTO(Miembro miembro) {
	        MiembroDTO dto = new MiembroDTO();
	        dto.setId(miembro.getId());
	        dto.setNombre(miembro.getNombre());
	        dto.setApellido(miembro.getApellido());
	        dto.setDni(miembro.getDni());
	        dto.setEmail(miembro.getEmail());
	        return dto;
	    }

	    private CursoDTO toCursoDTO(Curso curso) {
	        CursoDTO dto = new CursoDTO();
	        dto.setId(curso.getId());
	        dto.setNombre(curso.getNombre());
	        return dto;
	    }

	    private AsistenciaDTO toAsistenciaDTO(Asistencia asistencia) {
	        AsistenciaDTO dto = new AsistenciaDTO();
	        dto.setId(asistencia.getId());
	        dto.setFecha(asistencia.getFecha().getFecha().toString());//toString()); 
	        return dto;
	    }
}
