package com.charly.sbSec3Jwt.escuelaRural.curso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CursoDTO {
	  private Long id;
	  private String nombre;
}
