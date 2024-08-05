package com.charly.sbSec3Jwt.escuelaRural.alumno;

import java.util.List;

import com.charly.sbSec3Jwt.escuelaRural.asistencia.AsistenciaDTO;
import com.charly.sbSec3Jwt.escuelaRural.curso.CursoDTO;
import com.charly.sbSec3Jwt.escuelaRural.miembro.MiembroDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlumnoDTO {
	private Long id;
	private MiembroDTO miembro;
	private CursoDTO curso;
	private List<AsistenciaDTO> asistencias;
}
