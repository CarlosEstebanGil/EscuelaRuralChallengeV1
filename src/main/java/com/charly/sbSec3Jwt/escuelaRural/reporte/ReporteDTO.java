package com.charly.sbSec3Jwt.escuelaRural.reporte;


import java.time.LocalDateTime;

import com.charly.sbSec3Jwt.escuelaRural.fecha.Fecha;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReporteDTO {
	   private Long id;
	    private LocalDateTime fecha;
	    private Fecha fechaEntity; // El objeto Fecha completo
	    private long cantidadAlumnosDelCurso;
	    private long cantidadAlumnosPresentes;
	    private long cantidadAlumnosAusentes;
	    private long cantidadAlumnosAusentesJustificados;
	    private long cantidadAlumnosAusentesNoJustificados;
}